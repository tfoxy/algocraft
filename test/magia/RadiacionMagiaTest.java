package magia;

import error.EnergiaInsuficienteException;
import error.EstadoYaExisteEnFichaException;
import error.UnicamenteObjetivoUnidadException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.natural.terreno.TerrenoEspacial;
import ficha.FichaTerrestre;
import ficha.protoss.unidad.Zealot;
import ficha.terran.unidad.NaveCiencia;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;
import stats.BarrasEscudoVidaEnergia;
import stats.IBarras;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class RadiacionMagiaTest {

    private static class NaveCienciaCargadaDummy extends NaveCiencia {
        private static final BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
                new BarrasEscudoVidaEnergia.Builder()
                        .vida(200)
                        .energiaPorTurno(10)
                        .energiaMaxima(200);

        NaveCienciaCargadaDummy(int energiaActual) {
            this.barras = BARRAS_BUILDER.energiaActual(energiaActual).build();
        }
    }

    private Magia magia;
    private Tablero mapa;
    private Gaia gaia;
    private Jugador jugador;
    private Jugador jugadorEnemigo;
    private Coordenada coordenada = new Coordenada(3, 3);
    private Coordenada coordenadaVecina = new Coordenada(3, 4);
    private Coordenada3d coordenadaTerrestre = new Coordenada3d(3, 3, 1);
    private FichaAerea naveCiencia;

    @Before
    public void initialize() {
        magia = new RadiacionMagia();
        mapa = new Tablero(10, 10);
        gaia = new Gaia();
        jugador = new Jugador("nombre", Raza.TERRAN);
        jugadorEnemigo = new Jugador("enemigo", Raza.PROTOSS);
    }

    private void ponerNaveCiencia(int energiaInicial) {
        naveCiencia = new NaveCienciaCargadaDummy(energiaInicial);
        naveCiencia.setBasico(jugador, mapa, coordenada);
        naveCiencia.ponerEnJuego();
    }

    private Ficha ponerUnidadEnemiga() {
        FichaTerrestre fichaEnemiga = new Zealot();
        fichaEnemiga.setBasico(jugadorEnemigo, mapa, coordenada);
        fichaEnemiga.ponerEnJuego();
        return fichaEnemiga;
    }

    private Ficha ponerUnidadEnemigaVecina() {
        FichaTerrestre fichaEnemiga = new Zealot();
        fichaEnemiga.setBasico(jugadorEnemigo, mapa, coordenadaVecina);
        fichaEnemiga.ponerEnJuego();
        return fichaEnemiga;
    }


    @Test(expected = UnicamenteObjetivoUnidadException.class)
    public void noSePuedeAplicarAFichaEspacial() {
        TerrenoEspacial espacio = new TerrenoEspacial();
        espacio.setBasico(gaia, mapa, coordenada);
        espacio.ponerEnJuego();

        ponerNaveCiencia(magia.coste() * 2);

        magia.realizar(naveCiencia, coordenadaTerrestre);
    }

    @Test(expected = EnergiaInsuficienteException.class)
    public void noHaySuficienteEnergiaParaRealizar() {
        ponerNaveCiencia(magia.coste() - 1);
        ponerUnidadEnemiga();

        magia.realizar(naveCiencia, coordenadaTerrestre);
    }

    @Test
    public void haySuficienteEnergiaParaRealizar() {
        ponerNaveCiencia(magia.coste());
        ponerUnidadEnemiga();

        magia.realizar(naveCiencia, coordenadaTerrestre);
    }

    @Test(expected = EstadoYaExisteEnFichaException.class)
    public void noSePuedeAplicarDosVecesEnLaMismaUnidad() {
        ponerNaveCiencia(magia.coste() * 2);
        ponerUnidadEnemiga();

        magia.realizar(naveCiencia, coordenadaTerrestre);
        magia.realizar(naveCiencia, coordenadaTerrestre);
    }

    @Test
    public void realizaDanioALaUnidadAlPasarTurnoDeUnidad() {
        ponerNaveCiencia(magia.coste());
        Ficha ficha = ponerUnidadEnemiga();
        IBarras barras = ficha.barras();

        int vidaTotalInicial = barras.vidaActual() + barras.escudoActual();

        magia.realizar(naveCiencia, coordenadaTerrestre);
        ficha.pasarTurno();

        int vidaTotal = barras.vidaActual() + barras.escudoActual();

        assertThat(vidaTotal, lessThan(vidaTotalInicial));
    }

    @Test
    public void realizaDanioALaUnidadAereaQueEsteDebajoDeUnidadIrradiada() {
        ponerNaveCiencia(magia.coste());
        Ficha ficha = ponerUnidadEnemiga();
        IBarras barras = naveCiencia.barras();

        int vidaTotalInicial = barras.vidaActual() + barras.escudoActual();

        magia.realizar(naveCiencia, coordenadaTerrestre);
        ficha.pasarTurno();

        int vidaTotal = barras.vidaActual() + barras.escudoActual();

        assertThat(vidaTotal, lessThan(vidaTotalInicial));
    }

    @Test
    public void realizaDanioALaUnidadTerrestreVecina() {
        ponerNaveCiencia(magia.coste());
        Ficha ficha = ponerUnidadEnemiga();
        Ficha fichaVecina = ponerUnidadEnemigaVecina();
        IBarras barras = fichaVecina.barras();

        int vidaTotalInicial = barras.vidaActual() + barras.escudoActual();

        magia.realizar(naveCiencia, coordenadaTerrestre);
        ficha.pasarTurno();

        int vidaTotal = barras.vidaActual() + barras.escudoActual();

        assertThat(vidaTotal, lessThan(vidaTotalInicial));
    }

}

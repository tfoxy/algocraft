package magia;

import error.EnergiaInsuficienteException;
import error.UnicamenteObjetivoNoNaturalException;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaEspacial;
import ficha.FichaTerrestre;
import ficha.protoss.unidades.Zealot;
import ficha.terrans.unidad.NaveCiencia;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;
import stats.BarrasEscudoVidaEnergia;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

public class RadiacionMagiaTest {

    private static class NaveCienciaCargadaDummy extends NaveCiencia {
        private static BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
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
    private Coordenada3d coordenadaTerrestre = new Coordenada3d(3, 3, 1);
    private Coordenada3d coordenadaAerea = new Coordenada3d(3, 3, 2);
    private ExtrategiaConstrucccionOP moduloAux;
    private FichaAerea naveCiencia;

    @Before
    public void initialize() {
        moduloAux = new ExtrategiaConstrucccionOP();
        magia = new RadiacionMagia();
        mapa = new Tablero(10, 10);
        gaia = new Gaia();
        jugador = new Jugador("nombre", Raza.TERRAN);
        jugadorEnemigo = new Jugador("enemigo", Raza.PROTOSS);
    }

    private void ponerNaveCiencia(int energiaInicial) {
        naveCiencia = new NaveCienciaCargadaDummy(energiaInicial);
        naveCiencia.setBasico(jugador, mapa, coordenada);
        moduloAux.ponerEnJuego(naveCiencia);
    }

    private Ficha ponerUnidadEnemiga() {
        FichaTerrestre fichaEnemiga = new Zealot();
        fichaEnemiga.setBasico(jugadorEnemigo, mapa, coordenada);
        moduloAux.ponerEnJuego(fichaEnemiga);
        return fichaEnemiga;
    }


    @Test(expected = UnicamenteObjetivoNoNaturalException.class)
    public void noSePuedeAplicarAFichaEspacial() {
        FichaEspacial espacio = new FichaEspacial();
        espacio.setBasico(gaia, mapa, coordenada);
        moduloAux.ponerEnJuego(espacio);

        ponerNaveCiencia(200);

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

}

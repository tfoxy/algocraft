package magia;

import juego.Jugador;
import juego.Raza;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import stats.BarrasEscudoVidaEnergia;
import tablero.Altura;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.protoss.unidad.AltoTemplario;
import ficha.protoss.unidad.Scout;
import ficha.protoss.unidad.Zealot;

public class TormentaTest {

    private static class Caster extends AltoTemplario {
        private static final BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
                new BarrasEscudoVidaEnergia.Builder()
                        .vida(40).escudo(40)
                        .energiaPorTurno(15)
                        .energia(200);

        Caster() {
            this.barras = BARRAS_BUILDER.build();
        }
    }

    private FichaTerrestre caster;
    private FichaTerrestre victima;
    private FichaAerea victimaVoladora;
    private Tablero mapa;
    private Jugador jugador;
    private Jugador jugadorEnemigo;
    private TormentaPsionicaMagia magia;
    private Coordenada coordenada;
    private Coordenada3d coordenadaEnemigos;

    @Before
    public void initialize() {
        magia = new TormentaPsionicaMagia();
        mapa = new Tablero(10, 10);
        caster = new Caster();
        victima = new Zealot();
        victimaVoladora = new Scout();
        jugador = new Jugador("aliado", Raza.PROTOSS);
        jugadorEnemigo = new Jugador("enemigo", Raza.PROTOSS);

        coordenada = new Coordenada(3, 3);
        coordenadaEnemigos = new Coordenada3d(5, 5, Altura.TIERRA);

        caster.setBasico(jugador, mapa, coordenada);
        victima.setBasico(jugadorEnemigo, mapa, coordenadaEnemigos);
        victimaVoladora.setBasico(jugadorEnemigo, mapa, coordenadaEnemigos);

        caster.ponerEnJuego();
    }

    @Test
    public void noHacerNadaSiFalla() {
        magia.realizar(caster, coordenadaEnemigos);
        jugador.pasarTurno();
    }


    @Test
    public void danioDosUnidadesEnUnTurno() {
        victima.ponerEnJuego();
        victimaVoladora.ponerEnJuego();
        magia.realizar(caster, coordenadaEnemigos);
        jugador.pasarTurno();

        Assert.assertEquals(0, victima.barras().escudoActual());
        Assert.assertEquals(0, victimaVoladora.barras().escudoActual());
    }


    @Test
    public void danioDosUnidadesEnDosTurno() {
        victima.ponerEnJuego();
        victimaVoladora.ponerEnJuego();
        magia.realizar(caster, coordenadaEnemigos);
        jugador.pasarTurno();
        jugador.pasarTurno();

        Assert.assertTrue(victima.barras().estaMuerto());
        Assert.assertEquals(50, victimaVoladora.barras().vidaActual());
    }

    @Test
    public void danioUnaUnidadesEnUnTurno() {
        victima.ponerEnJuego();
        magia.realizar(caster, coordenadaEnemigos);
        jugador.pasarTurno();

        Assert.assertEquals(0, victima.barras().escudoActual());
    }

    @Test
    public void danioDosUnidadesEnUnTurnoPeroEsquiva() {
        victima.ponerEnJuego();
        magia.realizar(caster, coordenadaEnemigos);

        jugador.pasarTurno();

        victima.mover(Direccion.ABAJO);
        victima.mover(Direccion.ABAJO);

        jugador.pasarTurno();

        Assert.assertEquals(0, victima.barras().escudoActual());
        Assert.assertFalse(victima.barras().estaMuerto());
    }

    @Test
    public void danioDosUnidadesEnUnTurnoPeroFallaElPrimero() {
        victima.ponerEnJuego();
        magia.realizar(caster, coordenadaEnemigos);
        jugador.pasarTurno();
        victima.mover(Direccion.ABAJO);
        jugador.pasarTurno();
        Assert.assertEquals(0, victima.barras().escudoActual());
    }

}

package juego;

import ficha.Ficha;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.ITablero;
import tablero.Tablero;

public class JuegoTest {

    private ITablero mapa;
    private Jugador j1;
    private Jugador j2;
    private Ficha unidadJ1;
    private Ficha unidadJ2;
    private Juego juego;

    @Before
    public void initialize() {
        mapa = new Tablero(10, 10);
        j1 = new Jugador("PlayerProtoss", Raza.PROTOSS);
        j2 = new Jugador("PlayerTerran", Raza.TERRAN);

        unidadJ1 = j1.raza().nuevaUnidadBasica();
        unidadJ1.setBasico(j1, mapa, new Coordenada(2, 2));
        unidadJ1.ponerEnJuego();

        unidadJ2 = j2.raza().nuevaUnidadBasica();
        unidadJ2.setBasico(j2, mapa, new Coordenada(3, 3));
        unidadJ2.ponerEnJuego();

        juego = new Juego.Builder()
                .tablero(mapa)
                .agregarJugador(j1)
                .agregarJugador(j2)
                .build();
    }

    @Test
    public void cambiaDeJugadorAlPasarTurno() {
        Jugador jugador = juego.jugadorActual();
        juego.pasarTurno();
        Assert.assertNotEquals(jugador, juego.jugadorActual());
    }

    @Test
    public void noEstaTerminadoAlPasarTurno() {
        juego.pasarTurno();
        Assert.assertFalse(juego.estaTerminado());
    }

    @Test
    public void estaTerminadoAlMorirFichasDeJugador() {
        unidadJ2.muerete();
        juego.pasarTurno();
        Assert.assertTrue(juego.estaTerminado());
    }

    @Test
    public void terminaConVictoriaDeJugador1AlPerderJugador2() {
        unidadJ2.muerete();
        juego.pasarTurno();
        Assert.assertSame(j1, juego.jugadorActual());
    }

    @Test
    public void terminaConVictoriaDeJugador2AlPerderJugador1() {
        unidadJ1.muerete();
        juego.pasarTurno();
        Assert.assertSame(j2, juego.jugadorActual());
    }

    @Test
    public void terminaConVictoriaDeGaiaAlPerderAmbosJugadoresAlMismoTiempo() {
        unidadJ1.muerete();
        unidadJ2.muerete();
        juego.pasarTurno();
        Assert.assertSame(juego.gaia(), juego.jugadorActual());
    }

}

package ficha;

import static org.junit.Assert.assertEquals;

import juego.Jugador;

import org.junit.Before;
import org.junit.Test;

import stats.BarrasEscudoVidaEnergia;
import juego.Tecnologia;

public class BarrasTest {

    private BarrasEscudoVidaEnergia barras;
    private Jugador protoss;
    private Ficha nuevoEdificio;

    @Before
    public void initialize() {
        barras = new BarrasEscudoVidaEnergiaBuilder().setVidaMaxima(100).setEscudoMaximo(100).setEnergiaMaxima(100).createBarrasEscudoVidaEnergia();
        protoss = new Jugador("Proto", Tecnologia.PROTOSS, 500, 200);
        nuevoEdificio = new Pilon(protoss);
    }

    @Test
    public void destruirEscudoYVida() {
        barras.sufrirDanio(100, nuevoEdificio);
        assertEquals(barras.vidaActual(), 100);
        assertEquals(barras.escudoActual(), 0);

        barras.sufrirDanio(99, nuevoEdificio);
        assertEquals(barras.vidaActual(), 1);
        assertEquals(barras.escudoActual(), 0);
    }

    @Test
    public void currsePorTurno() {
        barras.sufrirDanio(100, nuevoEdificio);
        assertEquals(barras.escudoActual(), 0);
        barras.regenerar();
        assertEquals(barras.escudoActual(), 10);
    }

}

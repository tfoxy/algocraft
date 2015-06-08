package ficha;

import static org.junit.Assert.assertEquals;

import jugador.TablaJugador;

import org.junit.Before;
import org.junit.Test;

import stats.BarrasEscudoVidaEnergia;
import jugador.Tecnologia;

public class BarrasTest {

    private BarrasEscudoVidaEnergia barras;
    private TablaJugador protoss;
    private Ficha nuevoEdificio;

    @Before
    public void initialize() {
        barras = new BarrasEscudoVidaEnergia(100, 100, 100);
        protoss = new TablaJugador("Proto", Tecnologia.PROTOSS, 500, 200);
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
        barras.pasarTurno();
        assertEquals(barras.escudoActual(), 10);
    }

}

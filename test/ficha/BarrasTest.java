package ficha;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import stats.BarrasEscudoVidaEnergia;

public class BarrasTest {

    private BarrasEscudoVidaEnergia barras;

    @Before
    public void initialize() {
        barras = new BarrasEscudoVidaEnergia(100, 100);
    }

    @Test
    public void destruirEscudoYVida() {
        barras.sufrirDanio(100);
        assertEquals(100, barras.vidaActual());
        assertEquals(0, barras.escudoActual());

        barras.sufrirDanio(99);
        assertEquals(1, barras.vidaActual());
        assertEquals(0, barras.escudoActual());
    }

    @Test
    public void regenerarEscudoPorTurno() {
        barras.sufrirDanio(100);
        assertEquals(0, barras.escudoActual());
        barras.pasarTurno();
        assertEquals(10, barras.escudoActual());
    }

}

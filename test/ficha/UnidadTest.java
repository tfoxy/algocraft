package ficha;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stats.BarrasEscudoVidaEnergia;

public class UnidadTest {

    private static class UnidadTerrestreDummy extends UnidadTerrestre {
        public UnidadTerrestreDummy() {
            barras = new BarrasEscudoVidaEnergia(0);
            movimiento = 3;
            movimientoMaximo = 5;
        }
    }

    private UnidadTerrestreDummy unidad;

    @Before
    public void initialize() {
        unidad = new UnidadTerrestreDummy();
    }

    @Test
    public void recuperaMovimientoAlPasarDeTurno() {
        unidad.pasarTurno();
        Assert.assertEquals(unidad.movimientoMaximo(), unidad.movimiento());
    }

}

package ficha;

import Errores.ArgumentoNoPuedeSerNegativoException;
import Ficha.FichasNaturales.Volcan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuenteDeRecursoTest {

    @Test(expected = ArgumentoNoPuedeSerNegativoException.class)
    public void noPuedeInicializarseConCantidadNegativa() {
        new Volcan(-1);
    }

    @Test
    public void puedeCrearseConCeroDeRecursos() {
        Volcan volcan = new Volcan(0);

        assertEquals(0, volcan.getCantidadDeRecursos());
    }

    @Test
    public void extraeLoMaximoQuePuedeAlTenerRecursosDeMas() {
        Volcan volcan = new Volcan(50);

        assertEquals(10, volcan.extraer(10));
    }

    @Test
    public void noExtraeRecursosSiNoTiene() {
        Volcan volcan = new Volcan(0);

        assertEquals(0, volcan.extraer(10));
    }

    @Test
    public void extraeLoQueTieneSiSeQuiereExtraerDeMas() {
        Volcan volcan = new Volcan(5);

        assertEquals(5, volcan.extraer(10));
    }

}

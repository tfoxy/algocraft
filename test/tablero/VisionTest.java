package tablero;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VisionTest {

    private Tablero tablero;

    private Casilla getCasilla(int x, int y) {
        return tablero.getCasilla(new Coordenada(x, y));
    }

    @Before
    public void initialize() {
        tablero = new Tablero(12, 12);
    }

    @Test
    public void visionDistanciaMaxima() {
        CasillasVisibles vision = new CasillasVisibles();

        vision.verDesdeEstePunto(getCasilla(5, 5), 5);
        assertTrue(vision.puedeVer(getCasilla(5, 1)));
        assertTrue(vision.puedeVer(getCasilla(1, 5)));
        assertTrue(vision.puedeVer(getCasilla(5, 10)));
        assertTrue(vision.puedeVer(getCasilla(10, 5)));
        assertTrue(vision.puedeVer(getCasilla(8, 7)));

        assertFalse(vision.puedeVer(getCasilla(11, 1)));
    }

}

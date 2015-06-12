package tablero;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VisionTest {

    private Tablero tablero;

    @Before
    public void initialize() {
    tablero = new Tablero(12, 12);
    }

    @Test
    public void visionDistanciaMaxima() {
    	
    	CoordenadasVisibles vision = new CoordenadasVisibles();

        vision.verDesdeEstePunto(new Coordenada(5, 5), 5);


        assertTrue(vision.puedeVer(new Coordenada(5, 5)));
        assertTrue(vision.puedeVer(new Coordenada(5, 4)));
        assertTrue(vision.puedeVer(new Coordenada(5, 3)));
        assertTrue(vision.puedeVer(new Coordenada(5, 1)));
        assertTrue(vision.puedeVer(new Coordenada(1, 5)));
        assertTrue(vision.puedeVer(new Coordenada(5, 10)));
        assertTrue(vision.puedeVer(new Coordenada(10, 5)));
        assertTrue(vision.puedeVer(new Coordenada(8, 7)));

        assertFalse(vision.puedeVer(new Coordenada(11, 1)));
    }

}

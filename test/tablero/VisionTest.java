package tablero;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Tablero.CasillasVisibles;
import Tablero.Cordenada;

public class VisionTest {
    @Test
    public void visionDistanciaMaxima() {
        CasillasVisibles vision = new CasillasVisibles();
        Cordenada.Limites(10, 10);
        vision.VerDesdeEstePunto(new Cordenada(5, 5), 5);
        assertTrue(vision.PuedeVerEsto(new Cordenada(5, 0)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(0, 5)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(5, 10)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(10, 5)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(8, 7)));
    }

    @Test
    public void visionDistanciaMaximaNoFueraDelMapa() {
        CasillasVisibles vision = new CasillasVisibles();
        Cordenada.Limites(4, 10); //que pasa cone sto y lo de arriba?
        vision.VerDesdeEstePunto(new Cordenada(0, 0), 5);
        assertFalse(vision.PuedeVerEsto(new Cordenada(-5, 0)));
        assertFalse(vision.PuedeVerEsto(new Cordenada(5, 0)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(0, 5)));
        assertTrue(vision.PuedeVerEsto(new Cordenada(4, 0)));
    }

}

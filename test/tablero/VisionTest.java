package tablero;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Tablero.CasillasVisibles;
import Tablero.Cordenada;

public class VisionTest {
    @Test
    public void VisionDistanciaMaxima() {
        CasillasVisibles Vision = new CasillasVisibles();
        Cordenada.Limites(10,10); //preguntar que paas con esto y lo de abajo
        Vision.VerDesdeEstePunto(new Cordenada(5,5), 5);
        assertTrue(Vision.PuedeVerEsto(new Cordenada(5,0)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(0,5)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(5,10)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(10,5)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(8,7)));
    }

    @Test
    public void VisionDistanciaMaximaNoFueraDelMapa() {
        CasillasVisibles Vision = new CasillasVisibles();
        Cordenada.Limites(4,10); //que pasa cone sto y lo de arriba?
        Vision.VerDesdeEstePunto(new Cordenada(0,0), 5);
        assertFalse(Vision.PuedeVerEsto(new Cordenada(-5,0)));
        assertFalse(Vision.PuedeVerEsto(new Cordenada(5,0)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(0,5)));
        assertTrue(Vision.PuedeVerEsto(new Cordenada(4,0)));
    }
}
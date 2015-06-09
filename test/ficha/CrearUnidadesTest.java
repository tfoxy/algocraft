package ficha;

import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrearUnidadesTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
    }

    // TODO unidades deben crearse desde edificios
    /*
    @Test
    public void usarRecursosCorrectos() {
        Ficha nuevoEdificio = new Pilon();
        Ficha unidad = new Zealot();
        protoss.newFicha(nuevoEdificio);
        protoss.newFicha(unidad);

        assertEquals(2, protoss.poblcacionActual());
        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    */

}

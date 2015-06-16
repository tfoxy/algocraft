package ficha;

import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;

import ficha.protoss.edificio.Pilon;

import static org.junit.Assert.assertEquals;

public class ConstruccionTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
    }

    @Test
    public void queTardeElTiempoCorrecto() {
        Ficha pilon = new Pilon().enConstruccion();

        pilon.propietario(protoss);

        // TODO usar getter para tiempo de pilon y usar for
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();

        assertEquals(protoss.poblacionPosible(), 5);
    }

    // TODO hacer tests

}

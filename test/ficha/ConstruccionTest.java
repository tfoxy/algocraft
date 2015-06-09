package ficha;

import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;

import ficha.protoss.Pilon;

import static org.junit.Assert.assertEquals;

public class ConstruccionTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
    }

    @Test
    public void queTardeElTiempoCorrecto() {
        Pilon pilon = new Pilon();

        protoss.construir(pilon);

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

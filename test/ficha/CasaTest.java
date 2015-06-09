package ficha;

import juego.Jugador;
import juego.Raza;
import juego.RecursosDeJugador.Poblacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasaTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS);
    }

    @Test
    public void hayPoblacionPosibleAlCrearCasa() {
        Ficha ficha = new Pilon();
        Poblacion poblacion = protoss.recursos().poblacion();

        Assert.assertEquals(5, poblacion.posible());
    }

    @Test
    public void muereCasaPerdemosPoblacion() {
        Ficha ficha = new Pilon();
        Poblacion poblacion = protoss.recursos().poblacion();

        protoss.pasarTurno();

        ficha.muerete();

        Assert.assertEquals(0, poblacion.posible());

    }

}

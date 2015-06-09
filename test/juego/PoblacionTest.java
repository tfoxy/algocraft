package juego;

import error.RecursosInsuficientesException;
import juego.RecursosDeJugador.Poblacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PoblacionTest {

    private static final int POBLACION_MAXIMA = 100;

    private Poblacion poblacion;

    @Before
    public void initialize() {
        poblacion = new Poblacion(POBLACION_MAXIMA);
    }

    @Test
    public void actualComienzaConCero() {
        Assert.assertEquals(0, poblacion.actual());
    }


    @Test
    public void posibleComienzaConCero() {
        Assert.assertEquals(0, poblacion.posible());
    }


    @Test
    public void maximaSeRecibeComoParametroAlConstructor() {
        Assert.assertEquals(POBLACION_MAXIMA, poblacion.maxima());
    }


    @Test
    public void posibleCambiaDeValor() {
        poblacion.cambiarPosible(3);

        Assert.assertEquals(3, poblacion.posible());
    }


    @Test
    public void posibleNoAumentaMasQueLaMaxima() {
        Poblacion poblacion = new Poblacion(POBLACION_MAXIMA);

        poblacion.cambiarPosible(POBLACION_MAXIMA + 3);

        Assert.assertEquals(POBLACION_MAXIMA, poblacion.posible());
    }


    @Test
    public void posibleNoDisminuyeSiSeAgregoMuchaMasPoblacionQueLaMaxima() {
        poblacion.cambiarPosible(POBLACION_MAXIMA + 20);

        poblacion.cambiarPosible(-5);

        Assert.assertEquals(POBLACION_MAXIMA, poblacion.posible());
    }


    @Test(expected = RecursosInsuficientesException.class)
    public void actualCambiaDeValor() {
        poblacion.cambiarPosible(5);

        poblacion.cambiarActual(3);

        Assert.assertEquals(3, poblacion.actual());
    }


    @Test
    public void actualNoPuedeAumentarMasQueLaPosibleAlInicio() {
        poblacion.cambiarActual(5);
    }


    @Test(expected = RecursosInsuficientesException.class)
    public void actualNoPuedeAumentarMasQueLaPosibleAlAumentarPosible() {
        poblacion.cambiarPosible(3);

        poblacion.cambiarActual(5);
    }


    @Test
    public void actualNoCambiaAlDisminuirPosible() {
        poblacion.cambiarPosible(10); // 0/10

        poblacion.cambiarActual(8); // 8/10

        poblacion.cambiarPosible(-6); // 8/4

        Assert.assertEquals(8, poblacion.actual());
    }


    @Test
    public void actualPuedeCambiarCeroAlDisminuirPosible() {
        poblacion.cambiarPosible(10); // 0/10

        poblacion.cambiarActual(8); // 8/10

        poblacion.cambiarPosible(-6); // 8/4

        poblacion.cambiarActual(0); // 8/4

        Assert.assertEquals(8, poblacion.actual());
    }


    @Test
    public void actualPuedeDisminuirAlDisminuirPosible() {
        poblacion.cambiarPosible(10); // 0/10

        poblacion.cambiarActual(8); // 8/10

        poblacion.cambiarPosible(-6); // 8/4

        poblacion.cambiarActual(-2); // 6/4

        Assert.assertEquals(6, poblacion.actual());
    }


    @Test(expected = RecursosInsuficientesException.class)
    public void actualNoPuedeAumentarAlDisminuirPosible() {
        poblacion.cambiarPosible(10); // 0/10

        poblacion.cambiarActual(8); // 8/10

        poblacion.cambiarPosible(-6); // 8/4

        poblacion.cambiarActual(2); // 8+2/4
    }


    @Test
    public void actualPuedeAumentarMasAllaDeLaPosibleDeManeraForzada() {
        poblacion.aumentarActualForzadamente(5);

        Assert.assertEquals(5, poblacion.actual());
        Assert.assertEquals(0, poblacion.posible());
    }


}

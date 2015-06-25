package ficha;

import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import ficha.Ficha;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;
import ficha.protoss.unidad.Scout;
import ficha.protoss.unidad.Zealot;

public class MovimientoAereoTest {

    private Tablero mapa;
    private Ficha unidad;
    private Jugador jugador;
    private Coordenada lugar;
    private UnidadAerea otraUnidad;

    @Before
    public void initialize() {
        jugador = new Jugador("miNombre", Raza.PROTOSS);

        otraUnidad = new Scout();
        unidad = new Scout();

        mapa = new Tablero(20, 20);
        lugar = new Coordenada(3, 3);

        unidad.setBasico(jugador, mapa, lugar);
        unidad.ponerEnJuego();
    }


    @Test
    public void tieneTableroAlInsertarseSobreTablero() {
        Assert.assertSame(mapa, unidad.tablero());
    }


    @Test
    public void tieneCoordenadaAlInsertarseSobreTablero() {
        Assert.assertNotNull(unidad.coordenada());
        Assert.assertEquals(lugar, unidad.coordenada().proyeccion());
    }

    @Test
    public void puedeMoverseDondeNoHayNada() {
        unidad.intentarMovimiento(Direccion.ARRIBA);
    }

    @Test
    public void noPuedeMoverseDondeHayOtraUnidad() {
        otraUnidad.setBasico(jugador, mapa, new Coordenada(3, 4));
        otraUnidad.ponerEnJuego();

        Assert.assertFalse(unidad.intentarMovimiento(Direccion.ARRIBA));
    }


    @Test
    public void puedeInsertarseDondeHabiaOtraUnidadAntesDeMoverse() {
        unidad.intentarMovimiento(Direccion.ARRIBA);

        otraUnidad.setBasico(jugador, mapa, lugar);
        otraUnidad.ponerEnJuego();
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void noPuedeInsertarseDondeOtraUnidadSeMovio() {
        unidad.intentarMovimiento(Direccion.ARRIBA);

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3, 4));
        otraUnidad.ponerEnJuego();
    }


    @Test
    public void puedeMoverseDondeHabiaOtraUnidadAntesDeMoverse() {
        otraUnidad.setBasico(jugador, mapa, new Coordenada(3, 2));
        otraUnidad.ponerEnJuego();

        unidad.intentarMovimiento(Direccion.ARRIBA);
        Assert.assertTrue(otraUnidad.intentarMovimiento(Direccion.ARRIBA));
    }


    @Test
    public void noPuedeMoverseDondeOtraUnidadSeMovio() {
        otraUnidad.setBasico(jugador, mapa, new Coordenada(3, 5));
        otraUnidad.ponerEnJuego();

        otraUnidad.intentarMovimiento(Direccion.ABAJO);

        Assert.assertFalse(unidad.intentarMovimiento(Direccion.ARRIBA));
    }


    @Test
    public void gastaUnMovimientoAlMoverse() {
        int movimientoOriginal = unidad.movimiento();

        unidad.intentarMovimiento(Direccion.ABAJO);

        Assert.assertEquals(movimientoOriginal - 1, unidad.movimiento());
    }

}

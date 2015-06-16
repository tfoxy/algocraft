package estrategias;

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
import ficha.UnidadTerrestre;

import ficha.protoss.unidad.Zealot;

public class ExtrategiaVivaMovimientoTest {

    //en si tiene qeu tener los Test de todos sus modulos. Asi que se le tiene que agregar test mientras se les agrega modulo.
    private Tablero mapa;
    private Ficha unidad;
    private Jugador jugador;
    private Coordenada lugar;
    private UnidadTerrestre otraUnidad;

    @Before
    public void initialize() {
        jugador = new Jugador("miNombre", Raza.PROTOSS);

        otraUnidad = new Zealot();
        unidad = new Zealot();

        mapa = new Tablero(20, 20);
        lugar = new Coordenada(3, 3);

        unidad.setBasico(jugador, mapa, lugar);
        unidad.ponerEnJuego(); //no tiene sentido rebuildar aun algo que se usa solo para los test.
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

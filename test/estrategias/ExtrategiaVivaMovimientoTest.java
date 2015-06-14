package estrategias;

import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.UnidadTerrestre;

import ficha.protoss.Zealot;

public class ExtrategiaVivaMovimientoTest {

	//en si tiene qeu tener los Test de todos sus modulos. Asi que se le tiene que agregar test mientras se les agrega modulo.
    //private ModuloMover estrategiaMover;
    private Tablero mapa;
    private Ficha unidad;
    private Jugador jugador;
    private ExtrategiaConstrucccionOP moduloAux;
    private Coordenada lugar;
    private UnidadTerrestre otraUnidad;

    @Before
    public void initialize() {
        moduloAux = new ExtrategiaConstrucccionOP();
        jugador = new Jugador("miNombre", Raza.PROTOSS);
        
        otraUnidad = new Zealot(); 
        unidad = new Zealot();

        mapa = new Tablero(20, 20);
        lugar = new Coordenada (3, 3);
        
        unidad.setBasico(jugador, mapa, lugar);
        moduloAux.PonerEnJuego((FichaTerrestre)unidad); //no tiene sentido rebuildar aun algo que se usa solo para los test.
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
    public void noPuedeMoverseDondeNoHayOtraUnidad() {

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3,4));
        moduloAux.PonerEnJuego(otraUnidad);

        Assert.assertFalse(unidad.intentarMovimiento(Direccion.ARRIBA));
    }



    @Test
    public void puedeInsertarseDondeHabiaOtraUnidadAntesDeMoverse() {
        unidad.intentarMovimiento(Direccion.ARRIBA);
    	

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3,3));
        moduloAux.PonerEnJuego(otraUnidad);
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void noPuedeInsertarseDondeOtraUnidadSeMovio() {
        unidad.intentarMovimiento(Direccion.ARRIBA);
    	

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3,4));
        moduloAux.PonerEnJuego(otraUnidad);
    }


    @Test
    public void puedeMoverseDondeHabiaOtraUnidadAntesDeMoverse() {
        UnidadTerrestre otraUnidad = new Zealot();

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3,2));


        unidad.intentarMovimiento(Direccion.ARRIBA);
        otraUnidad.intentarMovimiento(Direccion.ARRIBA);
    }

    
    @Test
    public void noPuedeMoverseDondeOtraUnidadSeMovio() {

        otraUnidad.setBasico(jugador, mapa, new Coordenada(3,5));
        moduloAux.PonerEnJuego(otraUnidad);


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

package Extrategias;

import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;
import ficha.protoss.Zealot;
import ficha.protoss.unidades.Scout;

public class ExtrategiaVivaMovimientoAreaoTest {

    private Tablero mapa;
    private Ficha unidad;
    private Jugador jugador;
    private ExtrategiaConstrucccionOP moduloAux;
    private Coordenada lugar;
    private UnidadAerea otraUnidad;

    @Before
    public void initialize() {
        moduloAux = new ExtrategiaConstrucccionOP();
        jugador = new Jugador("miNombre", Raza.PROTOSS);
        
        otraUnidad = new Scout(); 
        unidad = new Scout();

        int tamanioDeMapa = unidad.movimientoMaximo() * 4;
        mapa = new Tablero(20, 20);
        lugar = new Coordenada(3, 3);
        
        unidad.setBasico(jugador, mapa, lugar);
        moduloAux.PonerEnJuego((FichaAerea)unidad);
    }


    @Test
    public void tieneTableroAlInsertarseSobreTablero() {
        Assert.assertSame(mapa, unidad.tablero());
    }


    @Test
    public void tieneCoordenadaAlInsertarseSobreTablero() {
        Assert.assertSame(lugar, unidad.coordenada());
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

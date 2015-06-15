package magia;

import static org.junit.Assert.assertEquals;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;

import org.junit.Before;
import org.junit.Test;

import tablero.Tablero;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.Ficha;
import ficha.protoss.edificios.Pilon;
import ficha.protoss.unidades.Zealot;

public class Expectro {
	
	Ficha ficha;
	Ficha cloneDeFicha;
	Ficha espectroDeFicha;
	
    @Before
    public void initialize() {
        ficha = new Zealot();
        cloneDeFicha = ficha.clone();
        espectroDeFicha = ficha.expectro();
    }

    @Test
    public void QueSeCloneBien() {

    	 //  assertEquals(ficha.ataqueTierra=cloneDeFicha.ataqueTierra); 
        // Abria que comparar sus ataques (que deverian ser iguals 
        //y sus barras de vidas que deverian ser distintas). pero ¿como hacerlo sin agregar tantos gets?
    }
}

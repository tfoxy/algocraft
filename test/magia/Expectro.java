package magia;

import org.junit.Before;
import org.junit.Test;

import ficha.Ficha;
import ficha.protoss.unidad.Zealot;

public class Expectro {

    Ficha ficha;
    Ficha cloneDeFicha;
    Ficha espectroDeFicha;

    @Before
    public void initialize() {
        ficha = new Zealot();
        cloneDeFicha = ficha.clone();
        espectroDeFicha = ficha.espectro();
    }

    @Test
    public void QueSeCloneBien() {

        //  assertEquals(ficha.ataqueTierra=cloneDeFicha.ataqueTierra);
        // Abria que comparar sus ataques (que deverian ser iguals 
        //y sus barras de vidas que deverian ser distintas). pero ¿como hacerlo sin agregar tantos gets?
    }
}

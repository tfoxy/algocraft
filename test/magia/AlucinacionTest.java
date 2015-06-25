package magia;

import org.junit.Before;
import org.junit.Test;

import ficha.Ficha;
import ficha.protoss.unidad.Zealot;

public class AlucinacionTest {

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
    public void queSeCloneBien() {

    }
}

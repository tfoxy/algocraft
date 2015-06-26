package magia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ficha.Ficha;
import ficha.protoss.unidad.Zealot;

public class AlucinacionDeZealotTest {

    Ficha ficha;
    Ficha clone;
    Ficha alucinacion;

    @Before
    public void initialize() {
        ficha = new Zealot();
        clone = ficha.clone();
        alucinacion = ficha.crearAlucinacion();
    }

    @Test
    public void cloneTieneLaMismaVidaQueElOriginal() {
        Assert.assertEquals(
                ficha.barras().vidaActual(),
                clone.barras().vidaActual()
        );
    }

    @Test
    public void cloneTieneElMismoEscudoQueElOriginal() {
        Assert.assertEquals(
                ficha.barras().escudoActual(),
                clone.barras().escudoActual()
        );
    }

    @Test
    public void cloneNoSufreDanioAlSufrirDanioElOriginal() {
        ficha.sufrirDanio(1);
        Assert.assertNotEquals(
                ficha.barras().escudoActual(),
                clone.barras().escudoActual()
        );
    }

    @Test
    public void alucinacionNoTieneDanioEnElAtaque() {
        Assert.assertEquals(0, alucinacion.ataqueTierra().danio());
        Assert.assertEquals(0, alucinacion.ataqueAire().danio());
    }

    @Test
    public void alucinacionTieneElMismoRangoQueElOriginal() {
        Assert.assertEquals(
                ficha.ataqueTierra().rango(),
                alucinacion.ataqueTierra().rango()
        );
    }
}

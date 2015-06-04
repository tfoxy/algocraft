package tablero;

import error.PosicionFueraDeLimiteException;
import ficha.natural.NodoMineral;
import ficha.FuenteDeRecurso;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableroTest {

    private Tablero tablero;


    @Before
    public void initialize() {
        tablero = new Tablero(6, 8);
    }


    @Test
    public void seCreaConLongitudXY() {
        int x = tablero.getLongitudX();
        int y = tablero.getLongitudY();

        Assert.assertEquals(x, 6);
        Assert.assertEquals(y, 8);
    }


    @Test
    public void noTiraErrorAlObtenerCasillaDeLasEsquinas() {
        Coordenada lugarSuperiorIzquierdo = new Coordenada(1, 1);
        Coordenada lugarInferiorDerecho = new Coordenada(6, 8);

        tablero.getCasilla(lugarSuperiorIzquierdo);
        tablero.getCasilla(lugarInferiorDerecho);
    }


    @Test(expected = PosicionFueraDeLimiteException.class)
    public void tiraErrorAlObtenerCasillaCeroCero() {
        Coordenada lugar = new Coordenada(0, 0);

        tablero.getCasilla(lugar);
    }


    @Test(expected = PosicionFueraDeLimiteException.class)
    public void tiraErrorAlObtenerCasillaFueraDeTablero() {
        Coordenada lugar = new Coordenada(7, 5);

        tablero.getCasilla(lugar);
    }


    @Test(expected = PosicionFueraDeLimiteException.class)
    public void tiraErrorAlInsertarEntidadFueraDeTablero() {
        FuenteDeRecurso recurso = new NodoMineral();
        Coordenada lugar = new Coordenada(7, 5);

        tablero.insertar(lugar, recurso);
    }


}

package tablero;

import Errores.PosicionFueraDeLimiteException;
import Ficha.FichasNaturales.NodoMineral;
import Ficha.FuenteDeRecurso;
import Tablero.Cordenada;
import Tablero.Tablero;
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
        Cordenada lugarSuperiorIzquierdo = new Cordenada(1, 1);
        Cordenada lugarInferiorDerecho = new Cordenada(6, 8);

        tablero.getCasilla(lugarSuperiorIzquierdo);
        tablero.getCasilla(lugarInferiorDerecho);
    }


    @Test(expected=PosicionFueraDeLimiteException.class)
    public void tiraErrorAlObtenerCasillaCeroCero() {
        Cordenada lugar = new Cordenada(0, 0);

        tablero.getCasilla(lugar);
    }


    @Test(expected=PosicionFueraDeLimiteException.class)
    public void tiraErrorAlObtenerCasillaFueraDeTablero() {
        Cordenada lugar = new Cordenada(7, 5);

        tablero.getCasilla(lugar);
    }


    @Test(expected=PosicionFueraDeLimiteException.class)
    public void tiraErrorAlInsertarEntidadFueraDeTablero() {
        FuenteDeRecurso recurso = new NodoMineral();
        Cordenada lugar = new Cordenada(7, 5);

        tablero.insertar(lugar, recurso);
    }


}

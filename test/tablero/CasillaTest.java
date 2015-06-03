package tablero;

import Errores.FichaEnTerrenoIncorrectoException;
import Errores.FichaSobreOtraFichaException;
import Ficha.Espectro;
import Ficha.Ficha;
import Ficha.FichaAerea;
import Ficha.FichasNaturales.NodoMineral;
import Ficha.FuenteDeRecurso;
import Ficha.Marine;
import Ficha.FichaTerrestre;
import Jugador.TablaJugador;
import Tablero.Casilla;
import Tablero.Casillero;
import Tablero.Terreno;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CasillaTest {

    private Casilla casilla;
    private TablaJugador jugador;

    @Before
    public void initialize() {
        casilla = new Casillero();
        jugador = new TablaJugador("Juan", "protoss");
    }


    @Test
    public void tieneTerrenoTerrestreAlCrearse() {
        Terreno terreno = casilla.getTerreno();

        Assert.assertSame(terreno, Terreno.TERRESTRE);
    }


    @Test
    public void noTieneFichaTerrestreAlCrearse() {
        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertTrue(ficha.EstasVacia());
    }


    @Test
    public void noTieneFichaAereaAlCrearse() {
        Ficha ficha = casilla.getFichaAerea();

        Assert.assertTrue(ficha.EstasVacia());
    }


    @Test
    public void puedeTenerTerrenoEspacial() {
        Terreno terreno = Terreno.ESPACIAL;

        casilla.modificar(terreno);

        Terreno terrenoObtenido = casilla.getTerreno();

        Assert.assertSame(terreno, terrenoObtenido);
    }


    @Test
    public void puedeTenerRecursos() {
        FuenteDeRecurso recurso = new NodoMineral();

        casilla.insertar(recurso);

        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertSame(recurso, ficha);
    }


    @Test
    public void puedeTenerUnidadTerrestre() {
        FichaTerrestre unidad = new Marine(jugador);

        casilla.insertar(unidad);

        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertSame(unidad, ficha);
    }


    @Test
    public void puedeTenerUnidadAerea() {
        FichaAerea unidad = new Espectro(jugador);

        casilla.insertar(unidad);

        Ficha ficha = casilla.getFichaAerea();

        Assert.assertSame(unidad, ficha);
    }


    @Test
    public void puedeTenerUnidadTerrestreYUnidadAereaAlMismoTiempo() {
        FichaTerrestre unidadTerrestre = new Marine(jugador);
        FichaAerea unidadAerea = new Espectro(jugador);

        casilla.insertar(unidadTerrestre);
        casilla.insertar(unidadAerea);

        Ficha fichaTerrestre = casilla.getFichaTerrestre();
        Ficha fichaAerea = casilla.getFichaAerea();

        Assert.assertSame(unidadTerrestre, fichaTerrestre);
        Assert.assertSame(unidadAerea, fichaAerea);
    }


    @Test(expected= FichaEnTerrenoIncorrectoException.class)
    public void tiraErrorAlInsertarUnidadDeTierraEnTerrenoEspacial() {
        Terreno terreno = Terreno.ESPACIAL;
        FichaTerrestre unidad = new Marine(jugador);

        casilla.modificar(terreno);
        casilla.insertar(unidad);
    }


    @Test(expected=FichaEnTerrenoIncorrectoException.class)
    public void tiraErrorAlInsertarTerrenoEspacialDondeHayUnidadDeTierra() {
        Terreno terreno = Terreno.ESPACIAL;
        FichaTerrestre unidad = new Marine(jugador);

        casilla.insertar(unidad);
        casilla.modificar(terreno);
    }


    @Test(expected= FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeTierraSobreRecurso() {
        FuenteDeRecurso recurso = new NodoMineral();
        FichaTerrestre unidad = new Marine(jugador);

        casilla.insertar(recurso);
        casilla.insertar(unidad);
    }


    @Test(expected=FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeTierraSobreUnidadDeTierra() {
        FichaTerrestre unaUnidad = new Marine(jugador);
        FichaTerrestre otraUnidad = new Marine(jugador);

        casilla.insertar(unaUnidad);
        casilla.insertar(otraUnidad);
    }


    @Test(expected=FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeAireSobreUnidadDeAire() {
        FichaAerea unaUnidad = new Espectro(jugador);
        FichaAerea otraUnidad = new Espectro(jugador);

        casilla.insertar(unaUnidad);
        casilla.insertar(otraUnidad);
    }


    @Test
    public void puedeEliminarLaUnidadTerrestreQueTiene() {
        FichaTerrestre unidad = new Marine(jugador);

        casilla.insertar(unidad);

        casilla.eliminarFichaTerrestre();

        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertTrue(ficha.EstasVacia());
    }


    @Test
    public void noTieneUnidadQueMuere() {
        // TODO implementar
    }

}

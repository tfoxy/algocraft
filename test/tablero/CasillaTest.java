package tablero;

import error.FichaSobreOtraFichaException;
import ficha.*;
import ficha.natural.NodoMineral;
import juego.Jugador;
import juego.Raza;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import juego.Tecnologia;

public class CasillaTest {

    private Casilla casilla;

    @Before
    public void initialize() {
        casilla = new CasillaDeTablero();
    }


    @Test
    public void noTieneFichaTerrestreAlCrearse() {
        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertTrue(ficha.estaVacia());
    }


    @Test
    public void noTieneFichaAereaAlCrearse() {
        Ficha ficha = casilla.getFichaAerea();

        Assert.assertTrue(ficha.estaVacia());
    }


    @Test
    public void puedeTenerAreasEspaciales() {
        FichaTerrestre areaEspacial = new FichaEspacial();

        casilla.insertar(areaEspacial);

        FichaTerrestre fichaObtenida = casilla.getFichaTerrestre();

        Assert.assertSame(areaEspacial, fichaObtenida);
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
        FichaTerrestre unidad = new Marine();

        casilla.insertar(unidad);

        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertSame(unidad, ficha);
    }


    @Test
    public void puedeTenerUnidadAerea() {
        FichaAerea unidad = new Espectro();

        casilla.insertar(unidad);

        Ficha ficha = casilla.getFichaAerea();

        Assert.assertSame(unidad, ficha);
    }


    @Test
    public void puedeTenerUnidadTerrestreYUnidadAereaAlMismoTiempo() {
        FichaTerrestre unidadTerrestre = new Marine();
        FichaAerea unidadAerea = new Espectro();

        casilla.insertar(unidadTerrestre);
        casilla.insertar(unidadAerea);

        Ficha fichaTerrestre = casilla.getFichaTerrestre();
        Ficha fichaAerea = casilla.getFichaAerea();

        Assert.assertSame(unidadTerrestre, fichaTerrestre);
        Assert.assertSame(unidadAerea, fichaAerea);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeTierraEnAreaEspacial() {
        FichaTerrestre areaEspacial = new FichaEspacial();
        FichaTerrestre unidad = new Marine();

        casilla.insertar(areaEspacial);
        casilla.insertar(unidad);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarAreaEspacialDondeHayUnidadDeTierra() {
        FichaTerrestre areaEspacial = new FichaEspacial();
        FichaTerrestre unidad = new Marine();

        casilla.insertar(unidad);
        casilla.insertar(areaEspacial);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeTierraSobreRecurso() {
        FuenteDeRecurso recurso = new NodoMineral();
        FichaTerrestre unidad = new Marine();

        casilla.insertar(recurso);
        casilla.insertar(unidad);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeTierraSobreUnidadDeTierra() {
        FichaTerrestre unaUnidad = new Marine();
        FichaTerrestre otraUnidad = new Marine();

        casilla.insertar(unaUnidad);
        casilla.insertar(otraUnidad);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void tiraErrorAlInsertarUnidadDeAireSobreUnidadDeAire() {
        FichaAerea unaUnidad = new Espectro();
        FichaAerea otraUnidad = new Espectro();

        casilla.insertar(unaUnidad);
        casilla.insertar(otraUnidad);
    }


    @Test
    public void puedeEliminarLaUnidadTerrestreQueTiene() {
        FichaTerrestre unidad = new Marine();

        casilla.insertar(unidad);

        casilla.eliminarFichaTerrestre();

        Ficha ficha = casilla.getFichaTerrestre();

        Assert.assertTrue(ficha.estaVacia());
    }


    @Test
    public void noTieneUnidadQueMuere() {
        // TODO implementar
    }

}

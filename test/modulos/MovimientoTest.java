package modulos;

import error.FichaSobreOtraFichaException;
import error.MovimientoInsuficienteException;
import estrategia.ficha.moduloDeEstrategias.ModuloMover;
import ficha.FichaTerrestre;
import ficha.terrans.Marine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;

public class MovimientoTest {

    private ModuloMover estrategiaMover;
    private Tablero mapa;
    private FichaTerrestre unidad;


    @Before
    public void initialize() {
        estrategiaMover = new ModuloMover();

        unidad = new Marine();

        int tamanioDeMapa = unidad.movimientoMaximo() * 4;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);
    }


    @Test
    public void tieneTableroAlInsertarseSobreTablero() {
        mapa.insertar(new Coordenada(3, 3), unidad);

        Assert.assertSame(mapa, unidad.tablero());
    }


    @Test
    public void tieneCoordenadaAlInsertarseSobreTablero() {
        Coordenada lugar = new Coordenada(3, 3);

        mapa.insertar(lugar, unidad);

        Assert.assertSame(lugar, unidad.coordenada());
    }


    @Test
    public void puedeMoverseDondeNoHayNada() {
        mapa.insertar(new Coordenada(3, 3), unidad);

        estrategiaMover.mover(unidad, Direccion.ARRIBA);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeMoverseDondeNoHayOtraUnidad() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), otraUnidad);

        estrategiaMover.mover(unidad, Direccion.ARRIBA);
    }


    @Test
    public void puedeInsertarseDondeHabiaOtraUnidadAntesDeMoverse() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 3), otraUnidad);

        estrategiaMover.mover(otraUnidad, Direccion.ARRIBA);

        mapa.insertar(new Coordenada(3, 3), unidad);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeInsertarseDondeOtraUnidadSeMovio() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 3), otraUnidad);

        estrategiaMover.mover(otraUnidad, Direccion.ARRIBA);

        mapa.insertar(new Coordenada(3, 4), unidad);
    }


    @Test
    public void puedeMoverseDondeHabiaOtraUnidadAntesDeMoverse() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 3), otraUnidad);
        mapa.insertar(new Coordenada(3, 2), unidad);

        estrategiaMover.mover(otraUnidad, Direccion.ARRIBA);
        estrategiaMover.mover(unidad, Direccion.ARRIBA);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeMoverseDondeOtraUnidadSeMovio() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 3), otraUnidad);
        mapa.insertar(new Coordenada(3, 4), unidad);

        estrategiaMover.mover(otraUnidad, Direccion.ARRIBA);
    }


    @Test
    public void gastaUnMovimientoAlMoverse() {
        mapa.insertar(new Coordenada(3, 3), unidad);

        int movimientoOriginal = unidad.movimiento();

        estrategiaMover.mover(unidad, Direccion.ARRIBA);

        Assert.assertEquals(movimientoOriginal - 1, unidad.movimiento());
    }


    @Test
    public void noGastaUnMovimientoAlNoMoverse() {
        FichaTerrestre otraUnidad = new Marine();

        mapa.insertar(new Coordenada(3, 4), otraUnidad);
        mapa.insertar(new Coordenada(3, 3), unidad);

        int movimientoOriginal = unidad.movimiento();

        boolean seMovio =
                estrategiaMover.intentarMovimiento(unidad, Direccion.ARRIBA);

        Assert.assertFalse(seMovio);
        Assert.assertEquals(movimientoOriginal, unidad.movimiento());
    }


    @Test
    public void noPuedeMoverseMasAllaDelLimiteDelMapa() {
        mapa.insertar(new Coordenada(1, 1), unidad);

        estrategiaMover.mover(unidad, Direccion.ARRIBA);
    }


    @Test
    public void noPuedeMoverseMasQueSuMaximo() {
        boolean seMovio;

        mapa.insertar(new Coordenada(1, 1), unidad);

        for (int i = 0; i < unidad.movimientoMaximo(); i++) {
            estrategiaMover.mover(unidad, Direccion.ARRIBA);
        }

        try {
            estrategiaMover.mover(unidad, Direccion.ARRIBA);
            Assert.fail();
        } catch (MovimientoInsuficienteException e) {
            // noop
        }
    }




}

package ficha;

import error.FichaSobreOtraFichaException;
import estrategia.ficha.EstrategiaMover;
import juego.Jugador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Movimiento;
import tablero.Tablero;
import juego.Tecnologia;

public class MovimientoTest {

    private EstrategiaMover estrategiaMover;
    private Tablero mapa;
    private FichaTerrestre unidad;
    private Jugador jugador;


    @Before
    public void initialize() {
        estrategiaMover = new EstrategiaMover();

        jugador = new Jugador("miNombre", Tecnologia.TERRAN);

        unidad = new Marine(jugador);

        int tamanioDeMapa = unidad.getMovimientoMaximo() * 4;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);
    }


    @Test
    public void puedeMoverseDondeNoHayNada() {
        mapa.insertar(new Coordenada(3, 3), unidad);

        boolean seMovio =
                estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertTrue(seMovio);
    }


    @Test
    public void noPuedeMoverseDondeNoHayOtraUnidad() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), otraUnidad);

        boolean seMovio =
                estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertFalse(seMovio);
    }


    @Test
    public void puedeInsertarseDondeHabiaOtraUnidadAntesDeMoverse() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 3), otraUnidad);

        estrategiaMover.intentarMovimiento(otraUnidad, Movimiento.ARRIBA);

        mapa.insertar(new Coordenada(3, 3), unidad);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeInsertarseDondeOtraUnidadSeMovio() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 3), otraUnidad);

        estrategiaMover.intentarMovimiento(otraUnidad, Movimiento.ARRIBA);

        mapa.insertar(new Coordenada(3, 4), unidad);
    }


    @Test
    public void puedeMoverseDondeHabiaOtraUnidadAntesDeMoverse() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 3), otraUnidad);
        mapa.insertar(new Coordenada(3, 2), unidad);

        estrategiaMover.intentarMovimiento(otraUnidad, Movimiento.ARRIBA);
        estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeMoverseDondeOtraUnidadSeMovio() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 3), otraUnidad);
        mapa.insertar(new Coordenada(3, 4), unidad);

        estrategiaMover.intentarMovimiento(otraUnidad, Movimiento.ARRIBA);
    }


    @Test
    public void gastaUnMovimientoAlMoverse() {
        mapa.insertar(new Coordenada(3, 3), unidad);

        int movimientoOriginal = unidad.getMovimiento();

        estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertEquals(movimientoOriginal - 1, unidad.getMovimiento());
    }


    @Test
    public void noGastaUnMovimientoAlNoMoverse() {
        FichaTerrestre otraUnidad = new Marine(jugador);

        mapa.insertar(new Coordenada(3, 4), otraUnidad);
        mapa.insertar(new Coordenada(3, 3), unidad);

        int movimientoOriginal = unidad.getMovimiento();

        estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertEquals(movimientoOriginal, unidad.getMovimiento());
    }


    @Test
    public void noPuedeMoverseMasAllaDelLimiteDelMapa() {
        mapa.insertar(new Coordenada(1, 1), unidad);

        boolean seMovio =
                estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertFalse(seMovio);
    }


    @Test
    public void noPuedeMoverseMasQueSuMaximo() {
        boolean seMovio;

        mapa.insertar(new Coordenada(1, 1), unidad);

        for (int i = 0; i < unidad.getMovimientoMaximo(); i++) {
            seMovio =
                    estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

            Assert.assertTrue(seMovio);
        }

        seMovio =
                estrategiaMover.intentarMovimiento(unidad, Movimiento.ARRIBA);

        Assert.assertFalse(seMovio);
    }




}

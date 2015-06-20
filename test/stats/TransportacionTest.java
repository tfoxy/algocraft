package stats;

import error.*;
import ficha.Ficha;
import ficha.UnidadTerrestre;
import ficha.protoss.edificio.Pilon;
import ficha.terran.unidad.Marine;
import ficha.terran.unidad.Golliat;
import org.junit.Assert;
import org.junit.Test;

public class TransportacionTest {

    private <T extends Ficha> T conMovimiento(T ficha) {
        ficha.recuperarPuntosDeMovimiento();
        return ficha;
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarAlTener0DeCapacidad() {
        Transportacion transportacion = Transportacion.VACIA;
        Ficha unidad = conMovimiento(new Marine());

        transportacion.cargar(unidad);
    }

    @Test(expected = MovimientoInsuficienteException.class)
    public void noPuedeCargarSiFichaACargarNoTieneMovimientoDisponible() {
        Transportacion transportacion = new Transportacion(8);
        Ficha unidad = new Marine();

        transportacion.cargar(unidad);
    }

    @Test
    public void cargarGastaUnMovimientoDeLaUnidad() {
        Transportacion transportacion = new Transportacion(8);
        Ficha unidad = conMovimiento(new Marine());

        int movimientoActual = unidad.movimiento();

        transportacion.cargar(unidad);

        Assert.assertEquals(movimientoActual - 1, unidad.movimiento());
    }

    @Test(expected = TransporteException.class)
    public void noPuedeCargarFichasInmovibles() {
        Ficha edificio = conMovimiento(new Pilon());
        Transportacion transportacion = new Transportacion(8);

        transportacion.cargar(edificio);
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarFichaDeOcupacion2ConCapacidad1() {
        Ficha unidad = conMovimiento(new Golliat());
        Transportacion transportacion = new Transportacion(1);

        transportacion.cargar(unidad);
    }

    @Test(expected = NoSePuedeCargarEntidadConOcupacionCeroException.class)
    public void noPuedeCargarFichaDeOcupacion0() {
        Ficha unidad = new UnidadTerrestre() {
            @Override
            public int ocupacionEnTransporte() {
                return 0;
            }
        };
        unidad.recuperarPuntosDeMovimiento();
        Transportacion transportacion = new Transportacion(8);

        transportacion.cargar(unidad);
    }

    @Test
    public void puedeCargar1FichasDeOcupacion2ConCapacidad2() {
        Transportacion transportacion = new Transportacion(2);

        transportacion.cargar(conMovimiento(new Golliat()));
    }

    @Test
    public void puedeCargar2FichasDeOcupacion1ConCapacidad2() {
        Transportacion transportacion = new Transportacion(2);

        transportacion.cargar(conMovimiento(new Marine()));
        transportacion.cargar(conMovimiento(new Marine()));
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarFichaDeOcupacion2ConSolamente1EspacioLibre() {
        Transportacion transportacion = new Transportacion(2);

        transportacion.cargar(conMovimiento(new Marine()));
        transportacion.cargar(conMovimiento(new Golliat()));
    }

    @Test(expected = TransporteNoContieneFichaException.class)
    public void noPuedeDescargarUnidadQueNoTiene() {
        Transportacion transportacion = new Transportacion(8);

        transportacion.cargar(conMovimiento(new Marine()));
        transportacion.descargar(conMovimiento(new Marine()));
    }


}

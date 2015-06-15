package stats;

import error.*;
import ficha.Ficha;
import ficha.UnidadTerrestre;
import ficha.protoss.edificios.Pilon;
import ficha.terrans.Marine;
import ficha.terrans.unidad.Golliat;
import org.junit.Assert;
import org.junit.Test;

public class TransportacionTest {

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarAlTener0DeCapacidad() {
        Transportacion transportacion = Transportacion.VACIA;
        Ficha unidad = new Marine();

        transportacion.cargar(unidad);
    }

    @Test(expected = MovimientoInsuficienteException.class)
    public void noPuedeCargarSiFichaACargarNoTieneMovimientoDisponible() {
        Transportacion transportacion = new Transportacion(8);
        Ficha unidad = new Marine();

        for (int i = 0; i < unidad.movimientoMaximo(); i++) {
            unidad.disminuirMovimiento();
        }

        transportacion.cargar(unidad);
    }

    @Test
    public void cargarGastaUnMovimientoDeLaUnidad() {
        Transportacion transportacion = new Transportacion(8);
        Ficha unidad = new Marine();

        int movimientoActual = unidad.movimiento();

        transportacion.cargar(unidad);

        Assert.assertEquals(movimientoActual - 1, unidad.movimiento());
    }

    @Test(expected = TransporteException.class)
    public void noPuedeCargarFichasInmovibles() {
        Ficha edificio = new Pilon();
        Transportacion transportacion = new Transportacion(8);

        transportacion.cargar(edificio);
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarFichaDeOcupacion2ConCapacidad1() {
        Ficha unidad = new Golliat();
        Transportacion transportacion =  new Transportacion(1);

        transportacion.cargar(unidad);
    }

    @Test(expected = NoSePuedeCargarUnidadConOcupacionCeroException.class)
    public void noPuedeCargarFichaDeOcupacion0() {
        Ficha unidad = new UnidadTerrestre() {
            @Override
            public int ocupacionEnTransporte() {
                return 0;
            }
        };
        Transportacion transportacion =  new Transportacion(8);

        transportacion.cargar(unidad);
    }

    @Test
    public void puedeCargar1FichasDeOcupacion2ConCapacidad2() {
        Transportacion transportacion =  new Transportacion(2);

        transportacion.cargar(new Golliat());
    }

    @Test
    public void puedeCargar2FichasDeOcupacion1ConCapacidad2() {
        Transportacion transportacion =  new Transportacion(2);

        transportacion.cargar(new Marine());
        transportacion.cargar(new Marine());
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarFichaDeOcupacion2ConSolamente1EspacioLibre() {
        Transportacion transportacion =  new Transportacion(2);

        transportacion.cargar(new Marine());
        transportacion.cargar(new Golliat());
    }

    @Test(expected = TransporteNoContieneFichaException.class)
    public void noPuedeDescargarUnidadQueNoTiene() {
        Transportacion transportacion =  new Transportacion(8);

        transportacion.cargar(new Marine());
        transportacion.descargar(new Marine());
    }




}

package ficha;

import error.FichaACargarDebeEstarDebajoDeTransporte;
import error.MovimientoInsuficienteException;
import error.TransporteNoContieneFichaException;
import stats.Transportacion;

public class TransporteAereo extends UnidadAerea {

    public TransporteAereo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Transporte debe tener capacidad positiva");
        }

        transportacion = new Transportacion(capacidad);
    }

    @Override
    public void cargar(Ficha ficha) {
        if (!coordenada2.proyeccion().equals(ficha.coordenada2.proyeccion())) {
            throw new FichaACargarDebeEstarDebajoDeTransporte();
        }

        transportacion.cargar(ficha);

        tablero.eliminarFicha(ficha.coordenada2);
    }

    @Override
    public void descargar(Ficha ficha) {
        if (!transportacion.contieneFicha(ficha)) {
            throw new TransporteNoContieneFichaException();
        }

        if (ficha.movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }

        tablero.insertar(coordenada, ficha);

        transportacion.descargar(ficha);
    }

}

package ficha;

import error.FichaACargarDebeEstarDebajoDeTransporte;
import error.FichaSobreOtraFichaException;
import error.MovimientoInsuficienteException;
import error.TransporteNoContieneFichaException;
import stats.Transportacion;
import tablero.Coordenada3d;

public class TransporteAereo extends UnidadAerea {

    public TransporteAereo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Transporte debe tener capacidad positiva");
        }

        transportacion = new Transportacion(capacidad);
    }

    @Override
    public void cargar(Ficha ficha) {
        if (!coordenada.proyeccion().equals(ficha.coordenada.proyeccion())) {
            throw new FichaACargarDebeEstarDebajoDeTransporte();
        }

        transportacion.cargar(ficha);

        tablero.eliminarFichaEn(ficha.coordenada);
    }

    private void validarDescarga(Ficha ficha) {
        if (!transportacion.contieneFicha(ficha)) {
            throw new TransporteNoContieneFichaException();
        }

        if (ficha.movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }

        Coordenada3d nuevaCoordenada = new Coordenada3d(coordenada, ficha.altura());

        this.verificarCoordenada(nuevaCoordenada);
    }

    @Override
    public void descargar(Ficha ficha) {
        this.validarDescarga(ficha);

        ficha.coordenada(coordenada.proyeccion());

        tablero.insertar(ficha);

        transportacion.descargar(ficha);
    }

}

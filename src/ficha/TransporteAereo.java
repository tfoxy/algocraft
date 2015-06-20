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

}

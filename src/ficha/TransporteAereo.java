package ficha;

import stats.Transportacion;

public class TransporteAereo extends UnidadAerea {

    public TransporteAereo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Transporte debe tener capacidad positiva");
        }

        transportacion = new Transportacion(capacidad);

        tipoDeFicha.add(TipoDeFicha.TRANSPORTE);
    }

}

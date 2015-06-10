package magia;

import error.EnergiaInsuficienteException;
import error.FueraDeRangoException;
import ficha.Ficha;
import tablero.Coordenada;
import tablero.Coordenada3d;

public abstract class Magia {
    private final int coste;
    private final int rango;

    public Magia(int coste, int rango) {
        this.coste = coste;
        this.rango = rango;
    }

    public void realizar(Ficha ficha, Coordenada3d objetivo) {
        verificarSiPuedeRealizarla(ficha, objetivo);
        verificarObjetivo(ficha, objetivo);

        // TODO aplicar(ficha, objetivo)
        // TODO quitar energia a ficha
    }

    private void verificarSiPuedeRealizarla(Ficha ficha, Coordenada objetivo) {
        if (ficha.barras().energiaActual() < coste) {
            throw new EnergiaInsuficienteException();
        }
        if (ficha.coordenada().distanciaAObjetivo(objetivo) > rango) {
            throw new FueraDeRangoException();
        }
    }

    protected void verificarObjetivo(Ficha ficha, Coordenada3d objetivo) {
        // noop: el objetivo es válido por defecto
    }

    // TODO protected abstract aplicar(Ficha ficha, Coordenada3d objetivo)

    int coste() {
        return coste;
    }

    int rango() {
        return rango;
    }

}

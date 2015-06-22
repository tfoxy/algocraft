package magia;

import error.EnergiaInsuficienteException;
import error.FueraDeRangoException;
import ficha.Ficha;
import tablero.Coordenada;
import tablero.Coordenada3d;

public abstract class Magia {
    private final String nombre;
    private final int coste;
    private final int rango;

    public Magia(String nombre, int coste, int rango) {
        this.nombre = nombre;
        this.coste = coste;
        this.rango = rango;
    }

    public final void realizar(Ficha caster, Coordenada3d objetivo) {
        verificarSiPuedeRealizarla(caster, objetivo);

        aplicar(caster, objetivo);

        caster.barras().quitarEnergia(coste);
    }

    protected abstract void aplicar(Ficha caster, Coordenada3d objetivo);

    private void verificarSiPuedeRealizarla(Ficha ficha, Coordenada objetivo) {
        if (ficha.barras().energiaActual() < coste) {
            throw new EnergiaInsuficienteException();
        }
        if (ficha.coordenada().distanciaAObjetivo(objetivo) > rango) {
            throw new FueraDeRangoException();
        }
    }

    public int coste() {
        return coste;
    }

    public int rango() {
        return rango;
    }

    public String nombre() {
        return nombre;
    }
}

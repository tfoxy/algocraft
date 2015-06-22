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
        verificarSiPuedeRealizarla(caster);
        verificarSiLlegaAlObjetivo(caster, objetivo);

        aplicar(caster, objetivo);

        caster.barras().quitarEnergia(coste);
        caster.disminuirMovimiento();
    }

    protected abstract void aplicar(Ficha caster, Coordenada3d objetivo);

    public void verificarSiPuedeRealizarla(Ficha caster) {
        if (caster.barras().energiaActual() < coste) {
            throw new EnergiaInsuficienteException();
        }
        caster.validarMovimientoSuficiente();
    }

    private void verificarSiLlegaAlObjetivo(Ficha caster, Coordenada3d objetivo) {
        if (caster.coordenada().distanciaAObjetivo(objetivo) > rango) {
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

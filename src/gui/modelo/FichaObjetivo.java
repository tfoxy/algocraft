package gui.modelo;

import ficha.Ficha;

public class FichaObjetivo {

    private final ElementObservable<AccionEnGrilla> cambioDeAccion;
    private final ElementObservable<Ficha> cambioDeFichaObjetivo;

    public FichaObjetivo() {
        cambioDeAccion = new ElementObservable<>(AccionEnGrilla.SELECCIONAR);
        cambioDeFichaObjetivo = new ElementObservable<>(null);
    }

    public void escucharCambioDeAccion(ElementObserver<AccionEnGrilla> o) {
        cambioDeAccion.addObserver(o);
    }

    public void cambiarAccion(AccionEnGrilla accionEnGrilla) {
        cambioDeAccion.setAndNotify(accionEnGrilla);
    }

    public void escucharCambioDeFichaObjetivo(ElementObserver<Ficha> o) {
        cambioDeFichaObjetivo.addObserver(o);
    }

    public void cambiarFichaObjetivo(Ficha ficha) {
        cambioDeFichaObjetivo.updateAndNotify(ficha);
        cambioDeAccion.set(AccionEnGrilla.SELECCIONAR);
    }

    public AccionEnGrilla accion() {
        return cambioDeAccion.get();
    }

    public Ficha ficha() {
        return cambioDeFichaObjetivo.get();
    }
}

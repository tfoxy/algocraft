package gui.modelo;

import ficha.Ficha;

public class FichaObjetivo {

    private static final AccionEnGrilla ACCION_POR_DEFECTO = AccionEnGrilla.SELECCION;

    private AccionEnGrilla accion;
    private AccionEnGrilla accionPrevia;
    private Ficha ficha;
    private Ficha fichaPrevia;

    private final Observable<FichaObjetivo> accionObservable;
    private final ObservableActions<AccionEnGrilla, FichaObjetivo> fichaObservables;

    public FichaObjetivo() {
        accion = ACCION_POR_DEFECTO;
        accionPrevia = accion;
        ficha = null;
        fichaPrevia = null;

        accionObservable = new Observable<>();
        fichaObservables = new ObservableEnumActions<>(AccionEnGrilla.class);
    }

    public Observable<FichaObjetivo> accionObservable() {
        return accionObservable;
    }

    public ObservableActions<AccionEnGrilla, FichaObjetivo> fichaObservables() {
        return fichaObservables;
    }

    public AccionEnGrilla accion() {
        return accion;
    }

    public Ficha ficha() {
        return ficha;
    }

    public AccionEnGrilla accionPrevia() {
        return accionPrevia;
    }

    public Ficha fichaPrevia() {
        return fichaPrevia;
    }

    public void cambiarAccion(AccionEnGrilla accion) {
        if (this.accion.equals(accion))
            return;

        this.accion = accion;
        this.accionObservable.notifyObservers(this);
        this.accionPrevia = accion;
    }

    public void cambiarFicha(Ficha ficha) {
        this.ficha = ficha;
        this.fichaObservables.notify(accion, this);
        this.fichaPrevia = ficha;
        retornarAccionPorDefecto();
    }

    public void retornarAccionPorDefecto() {
        cambiarAccion(ACCION_POR_DEFECTO);
    }
}

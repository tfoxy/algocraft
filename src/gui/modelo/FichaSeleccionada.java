package gui.modelo;

import error.JuegoException;
import error.UnicamenteObjetivoPropioException;
import ficha.Ficha;
import magia.Magia;
import tablero.Direccion;

public class FichaSeleccionada {
    private final FichaObjetivo fichaObjetivo;
    private final JugadorDeTurno jugadorDeTurno;
    private final Observable<Ficha> cambioDeFichaObservable;
    private final ObservableActions<AccionDeFicha, FichaSeleccionada> accionObservables;
    private JuegoLogger logger = JuegoLogger.EMPTY;

    private Ficha ficha;
    private Magia magiaSeleccionada;

    public FichaSeleccionada(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.fichaObjetivo = fichaObjetivo;
        this.jugadorDeTurno = jugadorDeTurno;

        this.cambioDeFichaObservable = new Observable<>();
        this.accionObservables = new ObservableEnumActions<>(AccionDeFicha.class);

        ObservableActions<AccionEnGrilla, FichaObjetivo> observables
                = fichaObjetivo.fichaObservables();
        observables.on(AccionEnGrilla.SELECCION, new SeleccionObserver());
        observables.on(AccionEnGrilla.ATAQUE, new AtaqueObserver());
    }


    private class SeleccionObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            seleccionar(data.ficha());
        }
    }

    private class AtaqueObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            atacar(data.ficha());
        }
    }


    public void seleccionar(Ficha ficha) {
        this.ficha = ficha;
        cambioDeFichaObservable.notifyObservers(ficha);
    }

    public void moverHacia(Direccion direccion) {
        try {
            validarPropietario("Solamente se pueden mover unidades propias");
            ficha().mover(direccion);
            fichaObjetivo.cambiarAccion(AccionEnGrilla.SELECCION);
            fichaObjetivo.cambiarFicha(ficha());
            accionObservables.notify(AccionDeFicha.MOVIMIENTO, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public void modoAtaque() {
        fichaObjetivo.cambiarAccion(AccionEnGrilla.ATAQUE);
    }

    public void atacar(Ficha objetivo) {
        try {
            validarPropietario("Solamente se puede atacar con unidades propias");
            ficha().atacar(objetivo);
            accionObservables.notify(AccionDeFicha.ATAQUE, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public Observable<Ficha> cambioDeFichaObservable() {
        return cambioDeFichaObservable;
    }

    public ObservableActions<AccionDeFicha, FichaSeleccionada> accionObservables() {
        return accionObservables;
    }

    public Ficha ficha() {
        return ficha;
    }

    public void setJuegoLogger(JuegoLogger juegoLogger) {
        this.logger = juegoLogger;
    }

    private void validarPropietario() {
        validarPropietario("Unidad no es propia");
    }

    private void validarPropietario(String errorMsg) {
        if (!ficha().propietario().equals(jugadorDeTurno.jugador())) {
            throw new UnicamenteObjetivoPropioException(errorMsg);
        }
    }
}

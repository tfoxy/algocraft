package controladores;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import error.JuegoException;
import error.UnicamenteObjetivoPropioException;
import gui.controlador.AnyEventListener;
import gui.controlador.KeyboardEvents;
import gui.modelo.AccionDeFicha;
import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.ObservableActions;
import gui.modelo.ObservableEnumActions;
import gui.modelo.Observer;
import magia.Magia;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha {

    private final FichaObjetivo fichaObjetivo;
    private final JugadorDeTurno jugadorDeTurno;
    private final Observable<Ficha> cambioDeFichaObservable;
    private final ObservableActions<AccionDeFicha, ControladorFicha> accionObservables;
    private JuegoLogger logger = JuegoLogger.EMPTY;

    private Ficha ficha;
    private Magia magiaSeleccionada;

    public ControladorFicha(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.fichaObjetivo = fichaObjetivo;
        this.jugadorDeTurno = jugadorDeTurno;

        this.cambioDeFichaObservable = new Observable<>();
        this.accionObservables = new ObservableEnumActions<>(AccionDeFicha.class);

        fichaObjetivo.fichaObservables().on(AccionEnGrilla.SELECCION, new Observer<FichaObjetivo>() {
            @Override
            public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
                seleccionar(data.ficha());
            }
        });

        fichaObjetivo.fichaObservables().on(AccionEnGrilla.ATAQUE, new Observer<FichaObjetivo>() {
            @Override
            public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
                atacar(data.ficha());
            }
        });
    }

    public ObservableActions<AccionDeFicha, ControladorFicha> accionObservables() {
        return accionObservables;
    }

    public void modoAtaque() {
        fichaObjetivo.cambiarAccion(AccionEnGrilla.ATAQUE);
    }

    private void atacar(Ficha objetivo) {
        try {
            validarPropietario("Solamente se puede atacar con unidades propias");
            ficha().atacar(objetivo);
            accionObservables.notify(AccionDeFicha.ATAQUE, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public Ficha ficha() {
        return ficha;
    }

    private void seleccionar(Ficha ficha) {
        this.ficha = ficha;
        cambioDeFichaObservable.notifyObservers(ficha);
    }

    public Observable<Ficha> cambioDeFichaObservable() {
        return cambioDeFichaObservable;
    }

    public void listenKeyboard(KeyboardEvents keyboard) {
        final int ev = KeyEvent.KEY_PRESSED;
        /* Movido a FichaView
        keyboard.addListener(ev, KeyEvent.VK_UP, new MovimientoListener(Direccion.ARRIBA));
        keyboard.addListener(ev, KeyEvent.VK_DOWN, new MovimientoListener(Direccion.ABAJO));
        keyboard.addListener(ev, KeyEvent.VK_RIGHT, new MovimientoListener(Direccion.DERECHA));
        keyboard.addListener(ev, KeyEvent.VK_LEFT, new MovimientoListener(Direccion.IZQUIERDA));*/
    }

    public void setJuegoLogger(JuegoLogger juegoLogger) {
        this.logger = juegoLogger;
    }


    //mover en todas las direcciones XD
    private class MovimientoListener extends AnyEventListener {

        private Direccion direccion;

        private MovimientoListener(Direccion direccion) {
            this.direccion = direccion;
        }

        @Override
        public void eventOcurred(AWTEvent e) {
            try {
                validarPropietario("Solamente se pueden mover unidades propias");
                ficha().mover(direccion);
                fichaObjetivo.cambiarAccion(AccionEnGrilla.SELECCION);
                fichaObjetivo.cambiarFicha(ficha());
                accionObservables.notify(AccionDeFicha.MOVIMIENTO, ControladorFicha.this);
            } catch (JuegoException exc) {
                logger.log(exc);
            }
        }
    }

    public AnyEventListener getMovimientoListener(Direccion direccion) {
        return new MovimientoListener(direccion);
    }

    public AnyEventListener getAtaqueListener() {
        return new AnyEventListener() {
            @Override
            public void eventOcurred(AWTEvent e) {
                modoAtaque();
            }
        };
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

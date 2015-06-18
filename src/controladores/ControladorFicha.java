package controladores;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import error.JuegoException;
import gui.controlador.AnyEventListener;
import gui.controlador.KeyboardListener;
import gui.controlador.ObservableEvent;
import gui.modelo.ObservableElement;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha {

    private ObservableElement<Ficha> ficha;
    private Observable cambioDeFichaObservable = new ObservableEvent();
    private Observable movimientoObservable = new ObservableEvent();

    public ControladorFicha(ObservableElement<Ficha> ficha) {
        this.ficha = ficha;

        ficha.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                cambioDeFichaObservable.notifyObservers();
            }
        });
    }

    public Ficha ficha() {
        return ficha.get();
    }


    public void observarCambioDeFicha(Observer o) {
        cambioDeFichaObservable.addObserver(o);
    }

    public void observarMovimiento(Observer o) {
        movimientoObservable.addObserver(o);
    }

    public void listenKeyboard(KeyboardListener keyboard) {
        final int ev = KeyEvent.KEY_PRESSED;

        keyboard.addListener(ev, KeyEvent.VK_UP, new MovimientoListener(Direccion.ARRIBA));
        keyboard.addListener(ev, KeyEvent.VK_DOWN, new MovimientoListener(Direccion.ABAJO));
        keyboard.addListener(ev, KeyEvent.VK_RIGHT, new MovimientoListener(Direccion.DERECHA));
        keyboard.addListener(ev, KeyEvent.VK_LEFT, new MovimientoListener(Direccion.IZQUIERDA));
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
                ficha().mover(direccion);
                movimientoObservable.notifyObservers();
            } catch (JuegoException exc) {
                // TODO avisar que no se pudo mover
            }
        }
    }

    public AnyEventListener getMovimientoListener(Direccion direccion) {
        return new MovimientoListener(direccion);
    }
}

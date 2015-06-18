package controladores;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import error.JuegoException;
import gui.controlador.AnyEventListener;
import gui.controlador.KeyboardEvents;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import tablero.Coordenada3d;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha {

    private ElementObservable<Ficha> cambioDeFichaObservable;
    private ElementObservable<Coordenada3d> movimientoObservable;

    public ControladorFicha(Ficha ficha) {
        this.cambioDeFichaObservable = new ElementObservable<>(ficha);
        this.movimientoObservable = new ElementObservable<>(ficha.coordenada());
    }

    public Ficha ficha() {
        return cambioDeFichaObservable.get();
    }

    public void seleccionar(Ficha ficha) {
        cambioDeFichaObservable.setAndNotify(ficha);
        movimientoObservable.set(ficha.coordenada());
    }

    public void observarCambioDeFicha(ElementObserver<Ficha> o) {
        cambioDeFichaObservable.addObserver(o);
    }

    public void observarMovimiento(ElementObserver<Coordenada3d> o) {
        movimientoObservable.addObserver(o);
    }

    public void listenKeyboard(KeyboardEvents keyboard) {
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
                movimientoObservable.setAndNotify(ficha().coordenada());
            } catch (JuegoException exc) {
                // TODO avisar que no se pudo mover
            }
        }
    }

    public AnyEventListener getMovimientoListener(Direccion direccion) {
        return new MovimientoListener(direccion);
    }
}

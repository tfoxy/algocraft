package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import error.JuegoException;
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


    //mover en todas las direcciones XD
    private class EscuchaBoton implements ActionListener {

        private Direccion direccion;

        private EscuchaBoton(Direccion direccion) {
            this.direccion = direccion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ficha().mover(direccion);
                movimientoObservable.notifyObservers();
            } catch (JuegoException exc) {
                // TODO avisar que no se pudo mover
            }
        }
    }

    public ActionListener getListenerMovimiento(Direccion direccion) {
        return new EscuchaBoton(direccion);
    }
}

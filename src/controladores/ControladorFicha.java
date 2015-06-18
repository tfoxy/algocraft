package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import gui.modelo.ObservableElement;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha extends Observable {

    private ObservableElement<Ficha> ficha;

    public ControladorFicha(ObservableElement<Ficha> ficha) {
        this.ficha = ficha;

        ficha.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                actualizarObservadores();
            }
        });
    }

    private void actualizarObservadores() {
        setChanged();
        notifyObservers(ficha());
    }

    public Ficha ficha() {
        return ficha.get();
    }

    //mover en todas las direcciones XD
    private class EscuchaBotonAbajo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ficha().intentarMovimiento(Direccion.ABAJO);
        }
    }

    public ActionListener getListenerBotonAbajo() {
        return new EscuchaBotonAbajo();
    }

    private class EscuchaBotonArriba implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ficha().intentarMovimiento(Direccion.ARRIBA);
        }
    }

    public ActionListener getListenerBotonArriba() {
        return new EscuchaBotonArriba();
    }

    private class EscuchaBotonDerecha implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ficha().intentarMovimiento(Direccion.DERECHA);
        }
    }

    public ActionListener getListenerBotonDerecha() {
        return new EscuchaBotonDerecha();
    }

    private class EscuchaBotonIzquierda implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ficha().intentarMovimiento(Direccion.IZQUIERDA);
        }
    }

    public ActionListener getListenerBotonIzquierda() {
        return new EscuchaBotonIzquierda();
    }
    //mover en todas las direcciones XD
}

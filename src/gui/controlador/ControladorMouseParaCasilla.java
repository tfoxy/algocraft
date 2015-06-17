package gui.controlador;

import ficha.Ficha;
import gui.modelo.ObservableElement;
import gui.vista.CasillaParaFicha;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorMouseParaCasilla extends MouseAdapter {

    private final CasillaParaFicha vista;
    private final ObservableElement<Ficha> observable;

    public ControladorMouseParaCasilla(CasillaParaFicha vista, ObservableElement<Ficha> observable) {
        this.vista = vista;
        this.observable = observable;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        observable.set(vista.ficha());
    }

}

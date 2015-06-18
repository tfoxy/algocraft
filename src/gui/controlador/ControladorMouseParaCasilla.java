package gui.controlador;

import controladores.ControladorFicha;
import gui.vista.CasillaParaFicha;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorMouseParaCasilla extends MouseAdapter {

    private final CasillaParaFicha vista;
    private final ControladorFicha control;

    public ControladorMouseParaCasilla(CasillaParaFicha vista, ControladorFicha control) {
        this.vista = vista;
        this.control = control;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        control.seleccionar(vista.ficha());
    }

}

package gui.controlador;

import controladores.ControladorFicha;
import gui.modelo.FichaObjetivo;
import gui.vista.CasillaParaFicha;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorMouseParaCasilla extends MouseAdapter {

    private final CasillaParaFicha vista;
    private final FichaObjetivo control;

    public ControladorMouseParaCasilla(CasillaParaFicha vista, FichaObjetivo fichaObjetivo) {
        this.vista = vista;
        this.control = fichaObjetivo;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        control.cambiarFichaObjetivo(vista.ficha());
    }

}

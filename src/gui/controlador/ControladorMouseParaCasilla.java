package gui.controlador;

import gui.modelo.FichaObjetivo;
import gui.vista.CasillaParaFicha;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorMouseParaCasilla extends MouseAdapter {

    private final CasillaParaFicha vista;
    private final FichaObjetivo modelo;

    public ControladorMouseParaCasilla(CasillaParaFicha vista, FichaObjetivo fichaObjetivo) {
        this.vista = vista;
        this.modelo = fichaObjetivo;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        modelo.cambiarFicha(vista.ficha());
    }

}

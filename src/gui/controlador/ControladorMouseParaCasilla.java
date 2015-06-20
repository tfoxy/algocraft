package gui.controlador;

import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.vista.CasillaParaFicha;

import javax.swing.SwingUtilities;
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
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            modelo.cambiarAccion(AccionEnGrilla.ATAQUE);
        }

        modelo.cambiarFicha(vista.ficha());
    }

}

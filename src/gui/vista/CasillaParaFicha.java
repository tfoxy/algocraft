package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorMouseParaCasilla;
import gui.modelo.ObservableElement;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class CasillaParaFicha extends JPanel {
    Ficha ficha;

    // Solo debe ser creado por CasillaVista
    CasillaParaFicha(Ficha ficha, ObservableElement<Ficha> fichaSeleccionada) {
        this.ficha = ficha;
        addMouseListener(new ControladorMouseParaCasilla(this, fichaSeleccionada));
    }

    public Ficha ficha() {
        return ficha;
    }

    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setColor(ficha.miColor());

        Dimension dimension = getSize();

        grafico.fillRect(0, 0, dimension.width, dimension.height);

        setToolTipText(ficha.nombre() + " " + ficha.coordenada().proyeccion().toString());
    }
}

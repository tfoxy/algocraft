package gui.vista;

import controladores.ControladorMouse;
import ficha.Ficha;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class CasillaParaFicha extends JPanel {
    Ficha ficha;

    // Solo debe ser creado por CasillaVista
    CasillaParaFicha(Ficha ficha) {
        this.ficha = ficha;
        addMouseListener(new ControladorMouse(this));
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

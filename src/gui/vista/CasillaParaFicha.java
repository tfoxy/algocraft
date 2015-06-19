package gui.vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.controlador.ControladorMouseParaCasilla;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import gui.modelo.FichaObjetivo;
import tablero.Coordenada3d;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class CasillaParaFicha extends JPanel {

    private final JLabel label = new JLabel();
    private boolean selected = false;
    Ficha ficha;

    // Solo debe ser creado por CasillaVista
    CasillaParaFicha(Ficha ficha, FichaObjetivo fichaObjetivo, ControladorFicha control) {
        this.ficha = ficha;

        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 8));
        add("Center", label);

        setBorder(null);

        addMouseListener(new ControladorMouseParaCasilla(this, fichaObjetivo));

        fichaObjetivo.escucharCambioDeFichaObjetivo(new FichaObserver());
        control.observarMovimiento(new MovimientoObserver());
    }

    public Ficha ficha() {
        return ficha;
    }

    private class FichaObserver implements ElementObserver<Ficha> {
        @Override
        public void update(ElementObservable<Ficha> o, Ficha prevElement) {
            if (ficha.equals(prevElement)) {
                selected = false;
                repaint();
            } else if (ficha.equals(o.get())) {
                selected = true;
                repaint();
            }
        }
    }

    private class MovimientoObserver implements ElementObserver<Coordenada3d> {
        @Override
        public void update(ElementObservable<Coordenada3d> o, Coordenada3d prevElement) {
            if (ficha.coordenada().equals(prevElement)) {
                selected = false;
                repaint();
            } else if (ficha.coordenada().equals(o.get())) {
                selected = true;
                repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setColor(ficha.miColor());

        Dimension dimension = getSize();

        if (selected) {
            grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);
        } else {
            grafico.fillRect(0, 0, dimension.width, dimension.height);
        }

        label.setText(ficha.nombre().length() > 0 ? ficha.nombre().substring(0, 1) : "");

        setToolTipText(ficha.nombre() + " " + ficha.coordenada().toString());
    }
}

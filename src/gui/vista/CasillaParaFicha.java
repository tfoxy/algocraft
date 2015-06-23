package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorMouseParaCasilla;
import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.Observable;
import gui.modelo.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class CasillaParaFicha extends JPanel {

    private final JLabel label = new ResizableLabel();
    private boolean selected = false;
    private Ficha ficha;

    // Solo debe ser creado por CasillaVista
    CasillaParaFicha(Ficha ficha, FichaObjetivo fichaObjetivo) {
        this.ficha = ficha;

        inicializarInterfaz();

        addMouseListener(new ControladorMouseParaCasilla(this, fichaObjetivo));

        fichaObjetivo.fichaObservables().on(AccionEnGrilla.SELECCION, new FichaObserver());
    }

    private void inicializarInterfaz() {
        Dimension dimension = getPreferredSize();
        setLayout(new BorderLayout());

        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        setPreferredSize(dimension);
    }

    // Solo debe ser usado por CasillaVista
    void cambiarFicha(Ficha ficha) {
        this.ficha = ficha;
        selected = false;

        this.repaint();
    }

    public Ficha ficha() {
        return ficha;
    }

    private class FichaObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> o, FichaObjetivo data) {
            if (ficha.equals(data.ficha())) {
                selected = true;
                repaint();
            } else if (ficha.equals(data.fichaPrevia())) {
                selected = false;
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

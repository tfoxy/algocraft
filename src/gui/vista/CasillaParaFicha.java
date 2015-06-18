package gui.vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.controlador.ControladorMouseParaCasilla;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import gui.modelo.FichaObjetivo;
import tablero.Coordenada3d;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

public class CasillaParaFicha extends JPanel {

    private boolean selected;
    Ficha ficha;

    // Solo debe ser creado por CasillaVista
    CasillaParaFicha(Ficha ficha, FichaObjetivo fichaObjetivo, ControladorFicha control) {
        this.ficha = ficha;
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



        setToolTipText(ficha.nombre() + " " + ficha.coordenada().proyeccion().toString());
    }
}

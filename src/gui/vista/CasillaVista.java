package gui.vista;

import ficha.Ficha;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;
import tablero.Coordenada3d;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observer;

public class CasillaVista extends JPanel {

    private CasillaParaFicha tierra;
    private CasillaParaFicha aire;

    private class CasillaParaFicha extends JPanel {
        private Ficha ficha;

        private CasillaParaFicha(Ficha ficha) {
            this.ficha = ficha;
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

    public CasillaVista(Coordenada coordenada, TableroObservable mapa) {
        tierra = new CasillaParaFicha(mapa.getFichaTerrestre(coordenada));
        aire = new CasillaParaFicha(mapa.getFichaAerea(coordenada));

        Observer observer = crearObserver(coordenada);
        mapa.addObserver(observer);

        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createRaisedSoftBevelBorder());

        add(aire);
        add(tierra);
    }

    private void repaintWith(Ficha ficha) {
        CasillaParaFicha casillaParaFicha;

        if (ficha.coordenada().z == Altura.TIERRA)
            casillaParaFicha = tierra;
        else
            casillaParaFicha = aire;

        casillaParaFicha.ficha = ficha;
        casillaParaFicha.repaint();
    }

    private Observer crearObserver(Coordenada coordenada) {
        return new CasillaObserver(coordenada) {
            @Override
            protected void updateVista(Ficha fichaNueva) {
                repaintWith(fichaNueva);
            }
        };
    }

}

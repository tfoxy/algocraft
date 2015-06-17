package gui.vista;

import ficha.Ficha;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.ITablero;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observer;

public class CasillaVista extends JPanel {

    private Ficha ficha;

    public CasillaVista(Coordenada coordenada, TableroObservable mapa) {
        this.ficha = mapa.getFichaTerrestre(coordenada);

        Observer observer = crearObserver(coordenada);
        mapa.addObserver(observer);
    }

    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setColor(ficha.miColor());
        Dimension dimension = getSize();

        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);

        setToolTipText(ficha.nombre());
    }

    private Observer crearObserver(Coordenada coordenada) {
        return new CasillaObserver(coordenada) {
            @Override
            protected void updateVista(Ficha fichaNueva) {
                ficha = fichaNueva;
                repaint();
            }
        };
    }

}

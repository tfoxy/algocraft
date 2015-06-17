package gui.vista;

import ficha.Ficha;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observer;

public class CasillaVista extends JPanel {

    private Ficha fichaTerrestre;
    private Ficha fichaAerea;

    public CasillaVista(Coordenada coordenada, TableroObservable mapa) {
        this.fichaTerrestre = mapa.getFichaTerrestre(coordenada);

        Observer observer = crearObserver(coordenada);
        mapa.addObserver(observer);
    }

    private void setFicha(Ficha ficha) {
        if (ficha.coordenada().z == Altura.TIERRA)
            fichaTerrestre = ficha;
        else
            fichaAerea = ficha;
    }

    @Override
    public void paintComponent(Graphics grafico) {
        super.paintComponent(grafico);
        grafico.setColor(fichaTerrestre.miColor());
        Dimension dimension = getSize();

        grafico.fill3DRect(0, 0, dimension.width, dimension.height, true);

        setToolTipText(fichaTerrestre.nombre() + " " + fichaTerrestre.coordenada().proyeccion().toString());
    }

    private Observer crearObserver(Coordenada coordenada) {
        return new CasillaObserver(coordenada) {
            @Override
            protected void updateVista(Ficha fichaNueva) {
                setFicha(fichaNueva);
                repaint();
            }
        };
    }

}

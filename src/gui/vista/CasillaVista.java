package gui.vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.modelo.ElementObservable;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Observer;

public class CasillaVista extends JPanel {

    private CasillaParaFicha tierra;
    private CasillaParaFicha aire;

    public CasillaVista(Coordenada coordenada, TableroObservable mapa, ControladorFicha control) {
        tierra = new CasillaParaFicha(mapa.getFichaTerrestre(coordenada), control);
        aire = new CasillaParaFicha(mapa.getFichaAerea(coordenada), control);

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

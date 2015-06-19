package gui.vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.modelo.ElementObservable;
import gui.modelo.FichaObjetivo;
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

    public CasillaVista(Coordenada coordenada, TableroObservable mapa,
                        FichaObjetivo fichaObjetivo) {
        tierra = new CasillaParaFicha(mapa.getFichaTerrestre(coordenada), fichaObjetivo);
        aire = new CasillaParaFicha(mapa.getFichaAerea(coordenada), fichaObjetivo);

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

        casillaParaFicha.cambiarFicha(ficha);
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

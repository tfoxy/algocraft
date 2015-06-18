package gui.vista;

import ficha.Ficha;
import gui.modelo.ObservableElement;
import gui.modelo.TableroObservable;
import tablero.Coordenada;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class GrillaView extends JPanel {

    public GrillaView(TableroObservable mapa, ObservableElement<Ficha> fichaSeleccionada) {
        setLayout(new GridLayout(mapa.getLongitudY(), mapa.getLongitudX()));


        for (int y = mapa.getLongitudY(); y > 0; y--) {
            for (int x = 1; x <= mapa.getLongitudX(); x++) {

                add(new CasillaVista(new Coordenada(x, y), mapa, fichaSeleccionada));

            }
        }
    }

}

package gui.vista;

import ficha.Ficha;
import gui.modelo.ObservableElement;
import gui.modelo.TableroObservable;
import tablero.Coordenada;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;

public class GrillaView extends JFrame {

    private ObservableElement<Ficha> fichaSeleccionada;

    public GrillaView(TableroObservable mapa) {
        fichaSeleccionada = new ObservableElement<Ficha>(mapa.getFichaTerrestre(new Coordenada(1, 1)));

        Container contenedor = getContentPane();

        contenedor.setLayout(new GridLayout(mapa.getLongitudY(), mapa.getLongitudX()));


        for (int y = mapa.getLongitudY(); y > 0; y--) {
            for (int x = 1; x <= mapa.getLongitudX(); x++) {

                contenedor.add(new CasillaVista(new Coordenada(x, y), mapa, fichaSeleccionada));

            }
        }
    }

    public ObservableElement<Ficha> fichaSeleccionada() {
        return fichaSeleccionada;
    }

}

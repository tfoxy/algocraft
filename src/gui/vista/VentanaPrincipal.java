package gui.vista;

import gui.modelo.TableroObservable;
import juego.Juego;
import tablero.Coordenada;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Juego juego, TableroObservable mapa) {
        setTitle("AlgoCraft");

        setSize(700, 500);

        setLocation(8, 0);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contenedor = getContentPane();

        contenedor.setLayout(new GridLayout(mapa.getLongitudY(), mapa.getLongitudX()));


        for (int y = mapa.getLongitudY(); y > 0; y--) {
            for (int x = 1; x <= mapa.getLongitudX(); x++) {

                contenedor.add(new CasillaVista(new Coordenada(x, y), mapa));

            }
        }

        setVisible(true);
    }
}

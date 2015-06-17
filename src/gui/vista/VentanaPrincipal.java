package gui.vista;

import gui.modelo.TableroObservable;
import juego.Juego;
import tablero.Coordenada;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Juego juego) {
        TableroObservable mapa = juego.hacerTableroObservable();

        setTitle("AlgoCraft");

        setSize(500, 400);

        setLocation(8, 0);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contenedor = getContentPane();

        contenedor.setLayout(new GridLayout(mapa.getLongitudX(), mapa.getLongitudY()));

        for (int x = 0; x < mapa.getLongitudX(); x++) {
            for (int y = 0; y < mapa.getLongitudY(); y++) {

                contenedor.add(new CasillaVista(new Coordenada(x + 1, y + 1), mapa));

            }
        }

        setVisible(true);
    }
}

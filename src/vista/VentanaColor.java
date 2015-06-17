package vista;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import auxiliares.ModeloObservable;
import auxiliares.Posicion;



public class VentanaColor extends JFrame {

    private static final long serialVersionUID = 7787529771808926374L;

    public VentanaColor(int cantidadFilasYColumnas, ModeloObservable modelo) {

        setTitle("Ejemplo de MVC | Color");
        Container contenedor = getContentPane();
        contenedor.setLayout(new GridLayout(cantidadFilasYColumnas, cantidadFilasYColumnas));

        for (int x = 0; x < cantidadFilasYColumnas; x++) {

            for (int y = 0; y < cantidadFilasYColumnas; y++) {

                contenedor.add(new VistaModeloDatoColor(new Posicion(x, y), modelo));

            }
        }

    }

}

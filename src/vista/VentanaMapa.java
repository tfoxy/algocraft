package vista;

import java.awt.Button;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Observable;

import javax.swing.JFrame;

import modelo.Modelo;

import tablero.Coordenada;
import tablero.Tablero;
import vista.VentanaRecursos.CloseListener;





public class VentanaMapa extends JFrame {
//bueno empesemos a toacar XD
//Para empesar voy a usar un mapa global. Pero de seguro vamos a tener que retocar mucho esto para hacerlo para cada jugador.
	//primero solo con terrestre
	
	private Tablero mapa;
	int filas;
	int columnas;
	Coordenada coordenada;
	String testo;
	private Frame frameTemp;
    Container contenedor;
    
    public VentanaMapa(Modelo modelo) {
		mapa = modelo.tablero;
		filas = mapa.getLongitudY();
		columnas = mapa.getLongitudX();
		
		
		//armado de la ventana
		frameTemp = new Frame("Mapa"); //creamos el marco
		frameTemp.add("North", new Label("mapa"));  //agregamos un titulo
		frameTemp.add("Center", contenedor); //agregamos el texto que muestra la temperatura
		frameTemp.setSize(300,100);  //seteamos las dimensiones del marco
		frameTemp.setVisible(true);  //mostramos el marco

		//agregamos el listener del evento de cerrado de la ventana		
		frameTemp.addWindowListener(new CloseListener());
		//agregamos el listener de los botones "Subir" y "Bajar"
		//Notar que los listeners se los pedimos al controlador		

		// Conectamos esta vista con el modelo
		
		
		
        setTitle("Mapa");
        contenedor = getContentPane();
        contenedor.setLayout(new GridLayout(filas,columnas));

        for (int x = 1; x < columnas; x++) {

            for (int y = 1; y < filas; y++) {

            	coordenada = new Coordenada(x,y);
            	if (mapa.hayEspacioTerreste(coordenada)) //en verdad hay que ponerle un color a los jugadores y poner que se carge ese color... Pero mientras
            	{ testo = "Vacio";
            	}else{ testo = "Lleno";
            	}
                contenedor.add(new Button(testo),coordenada);

            }
        }

        
    }

	public void update(Observable arg0, Object arg1) {
//haun tengo que pensar esto
	}
}

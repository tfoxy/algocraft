package vista;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import modelo.Modelo;


import juego.Recursos;


public class VentanaRecursos extends JFrame implements Observer{

	private Modelo recursos;
	private Frame frameTemp;
	private TextField textoRecursos = new TextField();
	
	public VentanaRecursos(Modelo recursos) {
		
		//armado de la ventana
		frameTemp = new Frame("Recursos"); //creamos el marco
		frameTemp.add("North", new Label("recursos"));  //agregamos un titulo
		frameTemp.add("Center", textoRecursos); //agregamos el texto que muestra la temperatura
		frameTemp.setSize(300,100);  //seteamos las dimensiones del marco
		frameTemp.setVisible(true);  //mostramos el marco

		//agregamos el listener del evento de cerrado de la ventana		
		frameTemp.addWindowListener(new CloseListener());
		frameTemp.enable(false);
		//agregamos el listener de los botones "Subir" y "Bajar"
		//Notar que los listeners se los pedimos al controlador		

		// Conectamos esta vista con el modelo
		this.recursos = recursos;
		this.recursos.addObserver(this); 
		
	}
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setTextoRecursos(recursos.recursos.mineral() + "Crital   " + recursos.recursos.gas() + "gas" + recursos.recursos.poblacion()); //reaser con Recursos de jugador.
		
	}

	private void setTextoRecursos(String string) {
		textoRecursos.setText(string);
		
	}
	
	
}

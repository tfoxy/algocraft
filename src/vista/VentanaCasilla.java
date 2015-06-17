package vista;


import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controladores.ControladorCasilla;

import tablero.Coordenada;
import tablero.Tablero;
import vista.VentanaRecursos.CloseListener;

public class VentanaCasilla extends JFrame {//esto no observa. Se usa una ves y se deveria morir.

	private Frame frameTemp;
	private Button botonAire = new Button("Subir");  //boton para subir la temperatura
	private Button botonTierra = new Button("Bajar");  //boton para bajar la temperatura
	
	public VentanaCasilla(Tablero tablero, Coordenada casilla, ControladorCasilla control) {
		
		//armado de la ventana
		frameTemp = new Frame("Casilla"); //creamos el marco

		
		
		botonAire = new Button(tablero.getFichaAerea(casilla).nombre());  //boton para subir la temperatura
		botonTierra = new Button(tablero.getFichaTerrestre(casilla).nombre());  //boton para bajar la temperatura
		
		Panel panelBotones = new Panel(); //creamos un panel para los botones
		panelBotones.add(botonAire); 
		panelBotones.add(botonTierra);  
		
		frameTemp.add("Center", panelBotones);  //agregamos el panel al marco
		
		
		frameTemp.setSize(300,100);  //seteamos las dimensiones del marco
		frameTemp.setVisible(true);  //mostramos el marco

		//agregamos el listener del evento de cerrado de la ventana		
		frameTemp.addWindowListener(new CloseListener());
		
		
		botonAire.addActionListener(control.getListenerBotonAire());
		botonTierra.addActionListener(control.getListenerBotonTierra());		

		// Conectamos esta vista con el modelo
 
		
	}
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}

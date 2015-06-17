package vista;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import tablero.Coordenada;
import tablero.Tablero;
import vista.VentanaCasilla.CloseListener;
import controladores.ControladorCasilla;
import controladores.ControladorFicha;
import ficha.Ficha;

public class VentanaFicha {

	private Frame frameTemp;
	private Button botonArriba = new Button("Arriba");
	private Button botonAbajo = new Button("Abajo");
	private Button botonIzquierda = new Button("Izquierda");
	private Button botonDerecha = new Button("Derecha");
	
	
	public VentanaFicha(Ficha ficha, ControladorFicha control) {
			frameTemp = new Frame(ficha.nombre()); 
	
			
			Panel panelBotones = new Panel();
			panelBotones.add(botonAbajo);
			panelBotones.add(botonArriba);
			panelBotones.add(botonDerecha);
			panelBotones.add(botonIzquierda);
			
			frameTemp.add("Center", panelBotones);
			
			
			frameTemp.setSize(300,100); 
			frameTemp.setVisible(true);
			
			frameTemp.addWindowListener(new CloseListener());
			
			
			botonAbajo.addActionListener(control.getListenerBotonAbajo());
			botonArriba.addActionListener(control.getListenerBotonArriba());
			botonDerecha.addActionListener(control.getListenerBotonDerecha());
			botonIzquierda.addActionListener(control.getListenerBotonIzquierda());
			// Conectamos esta vista con el modelo
	 
			
		}
		

		public static class CloseListener extends WindowAdapter
		{	public void windowClosing(WindowEvent e)
			{	e.getWindow().setVisible(false);
				System.exit(0);
			}
		}
}

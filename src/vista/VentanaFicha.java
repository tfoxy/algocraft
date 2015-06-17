package vista;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
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
	private Button botonOk = new Button("OK");  //boton para subir la temperatura
	
	public VentanaFicha(Ficha ficha, ControladorFicha control) {
			frameTemp = new Frame(ficha.nombre()); //creamos el marco

			
			Panel panelBotones = new Panel();
			panelBotones.add(botonOk); 
			
			frameTemp.add("Center", panelBotones);
			
			
			frameTemp.setSize(300,100); 
			frameTemp.setVisible(true);
			//agregamos el listener del evento de cerrado de la ventana		
			frameTemp.addWindowListener(new CloseListener());
			
			
			botonOk.addActionListener(control.getListenerBotonOk());	

			// Conectamos esta vista con el modelo
	 
			
		}
		
		public static class CloseListener extends WindowAdapter
		{	public void windowClosing(WindowEvent e)
			{	e.getWindow().setVisible(false);
				System.exit(0);
			}
		}
}

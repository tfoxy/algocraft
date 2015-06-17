package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ficha.Ficha;

import tablero.Coordenada;
import tablero.Tablero;
import vista.VentanaCasilla;
import vista.VentanaFicha;

public class ControladorCasilla {

	Ficha fichaAire;
	Ficha fichaTierra;
	
	public ControladorCasilla(Tablero tablero, Coordenada casilla) {
		fichaAire = tablero.getFichaAerea(casilla);
		fichaTierra = tablero.getFichaTerrestre(casilla);
	}

	private class EscuchaBotonAire implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ControladorFicha controladorFicha = new ControladorFicha (fichaAire); //creo controlador para la ficha
		new VentanaFicha (fichaAire, controladorFicha);//creo Ventana para la ficha
		}
	}
	
	public ActionListener getListenerBotonAire() {
		return new EscuchaBotonAire();
	}
	
	private class EscucharBotonTierra implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ControladorFicha controladorFicha = new ControladorFicha (fichaTierra);
		new VentanaFicha (fichaTierra, controladorFicha);
		}
	}
	
	public ActionListener getListenerBotonTierra() {
		return new EscucharBotonTierra();
	}
}

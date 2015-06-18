package controladores;

import ficha.Ficha;

import tablero.Coordenada;
import tablero.Tablero;

public class ControladorCasilla {

	Ficha fichaAire;
	Ficha fichaTierra;
	
	public ControladorCasilla(Tablero tablero, Coordenada casilla) {
		fichaAire = tablero.getFichaAerea(casilla);
		fichaTierra = tablero.getFichaTerrestre(casilla);
	}

	/*
	private class EscuchaBotonAire implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ControladorFicha controladorFicha = new ControladorFicha (fichaAire); //creo controlador para la ficha
		new FichaView (fichaAire, controladorFicha);//creo Ventana para la ficha
		}
	}
	
	public ActionListener getListenerBotonAire() {
		return new EscuchaBotonAire();
	}
	
	private class EscucharBotonTierra implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	
		ControladorFicha controladorFicha = new ControladorFicha (fichaTierra);
		new FichaView (fichaTierra, controladorFicha);
		}
	}

	public ActionListener getListenerBotonTierra() {
		return new EscucharBotonTierra();
	}*/
}

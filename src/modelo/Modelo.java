package modelo;

import tablero.Coordenada;
import tablero.Tablero;
import juego.Recursos;


public class Modelo extends java.util.Observable{
	
	public Recursos recursos;
	public Tablero tablero;


	public void ActualizarObservadores()
	{
		setChanged();
		notifyObservers();		
	}

	

}

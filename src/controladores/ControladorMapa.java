package controladores;

import tablero.Tablero;
import modelo.Modelo;

public class ControladorMapa {

	private Tablero mapa;
	
	public ControladorMapa(Modelo modelo) {
		mapa = modelo.tablero;
	}

}

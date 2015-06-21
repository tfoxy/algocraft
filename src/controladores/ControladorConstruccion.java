package controladores;

import ficha.Ficha;
import ficha.protoss.edificio.Acceso;
import ficha.protoss.edificio.ArchivosTemplarios;
import gui.controlador.AnyEventListener;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;

import juego.Jugador;

import tablero.Coordenada;
import tablero.Tablero;

public class ControladorConstruccion {
	
	private Tablero tablero;
	private Jugador jugador;
	
	public ControladorConstruccion(Tablero tablero, Jugador jugador) {
		this.tablero = tablero;
		this.jugador = jugador;
	}
	

	private class ConstruccionListener extends AnyEventListener {

		private String nombreFicha;
		private Ficha ficha;
		
		private ConstruccionListener (String ficha){
			this.nombreFicha = ficha;
		}
		
		@Override
		public void eventOcurred(AWTEvent e) {
			switch (nombreFicha){
			case "Acceso":
				ficha = new Acceso();
			case "ArchivosTemplarios":
				ficha = new ArchivosTemplarios();
			}
			ficha.setBasico(jugador, tablero, new Coordenada(1,1));
			ficha.ponerEnJuego();
			
		}
	
		
		
		
	}
		
	public ActionListener getConstruir(String ficha) {
		return new ConstruccionListener(ficha);
	}

}

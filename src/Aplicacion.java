import auxiliares.Juego2;

import modelo.Modelo;
import tablero.Coordenada;
import tablero.Tablero;

import vista.VentanaRecursos;

import controladores.ControladorRecursos;
import ficha.Ficha;
import ficha.protoss.unidad.Zealot;
import juego.Jugador;
import juego.Raza;
import juego.Recursos;

public class Aplicacion {//probando cosas
	
	
	public static void main(String args[]) {

		Modelo modelo = new Modelo();
		modelo.recursos= new Recursos(100,500);
		modelo.tablero = new Tablero (10,10);
		
        Juego2 juego = new Juego2();
        juego.preparar();
        
		//poner 1 zelot y todo eso//
		Ficha unidad = new Zealot();
		Jugador jugador = new Jugador("miNombre", Raza.PROTOSS);
        unidad.setBasico(jugador, juego.tablero, new Coordenada(4, 4));
        unidad.ponerEnJuego();
        
        juego.iniciar();
		

        
		ControladorRecursos controladorRecursos = new ControladorRecursos(modelo);

		new VentanaRecursos (modelo);
		modelo.ActualizarObservadores();
    }

}

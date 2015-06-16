import modelo.Modelo;
import tablero.Tablero;
import vista.VentanaMapa;
import vista.VentanaRecursos;
import controladores.ControladorMapa;
import controladores.ControladorRecursos;
import juego.Recursos;

public class Aplicacion {//probando cosas
	
	
	public static void main(String args[]) {

		Modelo modelo = new Modelo();
		modelo.recursos= new Recursos(100,500);
		modelo.tablero = new Tablero (10,10);
		ControladorRecursos controladorRecursos = new ControladorRecursos(modelo);
		ControladorMapa controladorMapa = new ControladorMapa(modelo);
		new VentanaMapa (modelo);
		new VentanaRecursos (modelo);
		modelo.ActualizarObservadores();
    }

}

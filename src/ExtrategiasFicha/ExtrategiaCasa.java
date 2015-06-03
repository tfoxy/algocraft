package ExtrategiasFicha;

import Ficha.FichaDeJugador;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;


public class ExtrategiaCasa extends ExtrategiaFicha {

	private TablaJugador Propetario;
	private Tablero Mapa;
	private Cordenada Lugar;

	public ExtrategiaCasa (TablaJugador propetario, Tablero mapa, Cordenada lugar){
		Propetario = propetario;
		Mapa = mapa;
		Lugar = lugar;
		propetario.AgregarPoblacionTotal(5);
		
	}
	
	public void Morir(FichaDeJugador casa) {
		Propetario.PerderPoblacionTotal(5);
		Propetario.PerderFicha(casa);
		Mapa.MuereFichaTerreste(Lugar, casa);
	}

}

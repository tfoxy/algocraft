package ExtrategiasFicha;

import Ficha.Ficha;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;

public class ExtrategiaUnidadSoldado extends ExtrategiaFicha {
	
	private TablaJugador Propetario;
	private Tablero Mapa;
	private Cordenada Lugar;
	private int PoblacionOpcupada;
	
	public ExtrategiaUnidadSoldado (TablaJugador propetario, Tablero mapa, Cordenada lugar, int poblacionOpcupada){
		Propetario = propetario;
		Mapa = mapa;
		Lugar = lugar;
		PoblacionOpcupada = poblacionOpcupada;
	}

	@Override
	public void Morir(Ficha Soldado) {
		Propetario.PerderPoblacionActual(PoblacionOpcupada);
		Propetario.PerderFicha(Soldado);
	}

}

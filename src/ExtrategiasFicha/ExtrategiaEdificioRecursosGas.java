package ExtrategiasFicha;

import Ficha.FichaDeJugador;
import Ficha.FichasNaturales.Volcan;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;

public class ExtrategiaEdificioRecursosGas extends ExtrategiaFicha {

	private TablaJugador Propetario;
	private Tablero Mapa;
	private Cordenada Lugar;
	private Volcan FuenteDeRecursos;
	

	public ExtrategiaEdificioRecursosGas(TablaJugador propetario, Tablero mapa,
			Cordenada lugar, Volcan fuenteDeRecursos) {
		Propetario = propetario;
		Mapa = mapa;
		Lugar = lugar;
		FuenteDeRecursos = fuenteDeRecursos;
	}
	
	public ExtrategiaFicha PasarTurno() {
		if (FuenteDeRecursos.Gaces > 0){
		Propetario.AgregarRecursosLineales(0,10);
		FuenteDeRecursos.Gaces = FuenteDeRecursos.Gaces - 10;}
		return this;
	}
	
	
	public void Morir(FichaDeJugador casa) {
		Propetario.PerderFicha(casa);
		Mapa.MuereFichaTerreste(Lugar, casa);
		Mapa.NewFichaTerreste(Lugar, FuenteDeRecursos);
	}

}

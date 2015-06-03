package ExtrategiasFicha;

import Ficha.Ficha;
import Ficha.FichasNaturales.Volcan;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;

public class ExtrategiaEdificioRecursosGas extends ExtrategiaFicha {

    private int CANTIDAD_EXTRAIDA_POR_TURNO = 10;

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

    @Override
	public ExtrategiaFicha PasarTurno() {
        int gasExtraido = FuenteDeRecursos.extraer(CANTIDAD_EXTRAIDA_POR_TURNO);
        Propetario.AgregarRecursosLineales(0, gasExtraido);
		return this;
	}


    @Override
	public void Morir(Ficha casa) {
		Propetario.PerderFicha(casa);
		Mapa.MuereFichaTerreste(Lugar, casa);
		Mapa.NewFichaTerreste(Lugar, FuenteDeRecursos);
	}

}

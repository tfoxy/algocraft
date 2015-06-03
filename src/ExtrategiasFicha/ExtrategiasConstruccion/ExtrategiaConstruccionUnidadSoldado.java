package ExtrategiasFicha.ExtrategiasConstruccion;

import ExtrategiasFicha.ExtrategiaFicha;
import ExtrategiasFicha.ExtrategiaUnidadSoldado;
import Ficha.Ficha;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class ExtrategiaConstruccionUnidadSoldado extends ExtrategiaConstrucion {
	
	public ExtrategiaConstruccionUnidadSoldado(Recursos coste,
			int turnosParaCrear, TablaJugador propetario, Tablero mapa,
			Cordenada lugar, ListaDeTecnologias lasTecnologiasNecesarias) {
		super(coste, turnosParaCrear, propetario, mapa, lugar, lasTecnologiasNecesarias);
		// TODO Auto-generated constructor stub
	}

	@Override
	public  ExtrategiaFicha PasarTurno() {
		TurnosParaCrear = TurnosParaCrear-1;
		if (TurnosParaCrear == 0){ return (new ExtrategiaUnidadSoldado (Propetario, Mapa, Lugar, Coste.PoblcacionActual()));
		}		
		return this;
	}

	@Override
	public void Morir(Ficha Soldado) {
		Propetario.PerderPoblacionActual(Coste.PoblcacionActual());
		Propetario.PerderFicha(Soldado);
		Mapa.MuereFichaTerreste(Lugar, Soldado);
	}
}
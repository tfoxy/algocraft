package ExtrategiasFicha.ExtrategiasConstruccion;

import ExtrategiasFicha.ExtrategiaCasa;
import ExtrategiasFicha.ExtrategiaFicha;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class ExtrategiaConstruccionPilon extends ExtrategiaConstrucion {

	public ExtrategiaConstruccionPilon(Recursos coste, int turnosParaCrear,
			TablaJugador propetario, Tablero mapa, Cordenada lugar,
			ListaDeTecnologias lasTecnologiasNecesarias) {
		super(coste, turnosParaCrear, propetario, mapa, lugar, lasTecnologiasNecesarias);

	}//se puede no repetir este Codigo?

	public  ExtrategiaFicha PasarTurno() {
		TurnosParaCrear = TurnosParaCrear-1;
		if (TurnosParaCrear == 0){ return (new ExtrategiaCasa(Propetario, Mapa, Lugar));
		}		
		return this;
	}
	
}

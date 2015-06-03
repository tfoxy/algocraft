package Ficha;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaEdificioRecursosGas;
import ExtrategiasFicha.ExtrategiaFicha;
import ExtrategiasFicha.ExtrategiasConstruccion.ExtrategiaConstruccionPilon;
import ExtrategiasFicha.ExtrategiasConstruccion.ExtrategiaConstrucionAsimilador;
import Ficha.FichasNaturales.Volcan;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;


public class Asimilador extends FichaDeJugador implements FichaTerrestre {

	private Recursos Coste;
	private int TurnosParaCrear;
	private TablaJugador Propetario;
	private ExtrategiaFicha Extrategia;
	private ListaDeTecnologias LasTecnologiasNecesarias;

	public Asimilador( TablaJugador propetario) {
		Propetario = propetario;
		Coste = new Recursos (100, 0);
		TurnosParaCrear = 6;
		Extrategia = new ExtrategiaEdificioRecursosGas(Propetario, null, null, new Volcan());
	}
	
	public Asimilador( TablaJugador propetario, Cordenada lugar, Tablero mapa) throws NoSePuedeCrear {
		Propetario = propetario;
		Coste = new Recursos (100, 0);
		TurnosParaCrear = 6;
		LasTecnologiasNecesarias = new ListaDeTecnologias();
		LasTecnologiasNecesarias.Agregar("Protos");
		Extrategia = new ExtrategiaConstrucionAsimilador (Coste, TurnosParaCrear, Propetario, mapa, lugar, LasTecnologiasNecesarias);
		if (Extrategia.MePuedeCrear()){
			Extrategia.Creame(this);}
	}
	
	public void PasarTurno() {
		Extrategia = Extrategia.PasarTurno();
	}

	@Override
	public void Muerete() {
		Extrategia.Morir(this);
		
	}

}

package Ficha;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaFicha;
import ExtrategiasFicha.ExtrategiasConstruccion.ExtrategiaConstruccionPilon;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;

import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class Pilón extends Edificio {

	private Recursos Coste;
	private int TurnosParaCrear;
	private TablaJugador Propetario;
	private ExtrategiaFicha Extrategia;
	private ListaDeTecnologias LasTecnologiasNecesarias;
	
	
	//constructorObseleto
	public Pilón( TablaJugador propetario) {
		Propetario = propetario;
		Coste = new Recursos (100, 0);
		TurnosParaCrear = 5;
		propetario.AgregarPoblacionTotal(5);
	}
	//constructor
	public Pilón(TablaJugador propetario, Cordenada lugar, Tablero mapa) throws NoSePuedeCrear{
		Propetario = propetario;
		Coste = new Recursos (100, 0, 0);
		TurnosParaCrear = 5;
		LasTecnologiasNecesarias = new ListaDeTecnologias();
		LasTecnologiasNecesarias.Agregar("Protos");
		Extrategia = new ExtrategiaConstruccionPilon(Coste, TurnosParaCrear, Propetario, mapa, lugar, LasTecnologiasNecesarias);
		
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

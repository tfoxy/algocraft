package Ficha;


import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaFicha;
import ExtrategiasFicha.ExtrategiasConstruccion.ExtrategiaConstruccionUnidadSoldado;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class Zealot extends Unidad implements FichaTerrestre {

	private Recursos Coste;
	private int TurnosParaCrear;
	private ExtrategiaFicha Extrategia;
	private ListaDeTecnologias LasTecnologiasNecesarias;
	
	public Zealot( TablaJugador propetario, Cordenada lugar, Tablero mapa) throws NoSePuedeCrear {
		Propetario = propetario;
		Coste = new Recursos (100, 0 ,2);
		TurnosParaCrear = 4;
		LasTecnologiasNecesarias = new ListaDeTecnologias();
		LasTecnologiasNecesarias.Agregar("Acceso");
		LasTecnologiasNecesarias.Agregar("Protos");
		Extrategia = new ExtrategiaConstruccionUnidadSoldado(Coste, TurnosParaCrear, Propetario, mapa, lugar, LasTecnologiasNecesarias);
		
		if (Extrategia.MePuedeCrear()){
			Extrategia.Creame(this);
		}
	}
	
	public Zealot( TablaJugador propetario) throws NoSePuedeCrear {
		Propetario = propetario;
		Coste = new Recursos (100, 0 ,2);
		TurnosParaCrear = 4;
		if (HayRecursosSuficientesParaCrearme (Coste)){
			propetario.GastaRecursos (Coste);
			propetario.NewFicha(this);
		}else{
			throw new NoSePuedeCrear("Faltan Recursos");
		}
			
	}

	@Override
	public void PasarTurno() {
	}

	@Override
	public void Muerete() {
		Extrategia.Morir(this);
		
	}

}

package ExtrategiasFicha.ExtrategiasConstruccion;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaEdificioRecursosGas;
import ExtrategiasFicha.ExtrategiaFicha;
import ExtrategiasFicha.ExtrategiaUnidadSoldado;
import Ficha.FichaDeJugador;
import Ficha.FichasNaturales.Volcan;
import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class ExtrategiaConstrucionAsimilador extends ExtrategiaConstrucion {
	
	private Volcan FuenteRecursos;
	public ExtrategiaConstrucionAsimilador(Recursos coste, int turnosParaCrear,
			TablaJugador propetario, Tablero mapa, Cordenada lugar,
			ListaDeTecnologias lasTecnologiasNecesarias) {
		super(coste, turnosParaCrear, propetario, mapa, lugar, lasTecnologiasNecesarias);
	}
	
	public boolean MePuedeCrear() throws NoSePuedeCrear {
		if ( !(Propetario.TengoSuficientesRecursos (Coste))){
			throw new NoSePuedeCrear("Faltan Recursos");			
		}
		if (!Mapa.HayUnaFuenteDeRecuros(Lugar).equals("Volcan")){
			throw new NoSePuedeCrear("No Es un Volcan");
		}	
		if (!(Propetario.TienesLasTecnologias (LasTecnologiasNecesarias))){
			throw new NoSePuedeCrear("No Tienes las tecnologias");
		}
		
		return true;
	}
 
	public void Creame( FichaDeJugador Nueva) {
	Propetario.GastaRecursos (Coste);			
	Propetario.NewFicha(Nueva);
	FuenteRecursos = (Volcan) Mapa.DameLaFichaTerresteEn(Lugar); //no se por que me olvida a hacer el Caste
	Mapa.NewFichaTerreste(Lugar, Nueva);
	}
	
	public  ExtrategiaFicha PasarTurno() {
		TurnosParaCrear = TurnosParaCrear-1;
		if (TurnosParaCrear == 0){ return (new ExtrategiaEdificioRecursosGas(Propetario, Mapa, Lugar, FuenteRecursos));
		}		
		return this;
	}

}

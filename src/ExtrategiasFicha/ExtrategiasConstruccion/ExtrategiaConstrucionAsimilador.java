package ExtrategiasFicha.ExtrategiasConstruccion;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaEdificioRecursosGas;
import ExtrategiasFicha.ExtrategiaFicha;
import Ficha.Ficha;
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

    @Override
	public boolean MePuedeCrear() throws NoSePuedeCrear {
		if ( !(Propetario.TengoSuficientesRecursos (Coste))){
			throw new NoSePuedeCrear("Faltan Recursos");			
		}
		// TODO solucionar sin usar instanceof
		if (!(Mapa.getCasilla(Lugar).getFichaTerrestre() instanceof Volcan)) {
			throw new NoSePuedeCrear("No Es un Volcan");
		}	
		if (!(Propetario.TienesLasTecnologias (LasTecnologiasNecesarias))){
			throw new NoSePuedeCrear("No Tienes las tecnologias");
		}
		
		return true;
	}

    @Override
	public void Creame(Ficha Nueva) {
        // TODO Solucionar sin castear
        FuenteRecursos = (Volcan) Mapa.DameLaFichaTerresteEn(Lugar);

		super.Creame(Nueva);
	}

    @Override
	public  ExtrategiaFicha PasarTurno() {
		TurnosParaCrear = TurnosParaCrear-1;
		if (TurnosParaCrear == 0) {
			return new ExtrategiaEdificioRecursosGas(Propetario, Mapa, Lugar, FuenteRecursos);
		}		
		return this;
	}

}

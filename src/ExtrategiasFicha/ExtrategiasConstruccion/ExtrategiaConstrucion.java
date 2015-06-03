package ExtrategiasFicha.ExtrategiasConstruccion;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaCasa;
import ExtrategiasFicha.ExtrategiaFicha;
import Ficha.FichaDeJugador;

import Jugador.Recursos;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;
import Tecnologia.ListaDeTecnologias;

public class ExtrategiaConstrucion extends ExtrategiaFicha {

		protected Recursos Coste;
		protected int TurnosParaCrear;
		protected TablaJugador Propetario;
		protected Tablero Mapa;
		protected Cordenada Lugar;
		protected ListaDeTecnologias LasTecnologiasNecesarias;

		public ExtrategiaConstrucion(Recursos coste, int turnosParaCrear, TablaJugador propetario, Tablero mapa, Cordenada lugar, ListaDeTecnologias lasTecnologiasNecesarias){
			Coste = coste;
			TurnosParaCrear = turnosParaCrear;
			Propetario = propetario;
			Mapa = mapa;
			Lugar = lugar;
			LasTecnologiasNecesarias = lasTecnologiasNecesarias;
		}
		
		public boolean MePuedeCrear() throws NoSePuedeCrear {
			if ( !(Propetario.TengoSuficientesRecursos (Coste))){
				throw new NoSePuedeCrear("Faltan Recursos");			
			}
			if (!(Mapa.HayEspacioTerreste (Lugar))){
				throw new NoSePuedeCrear("Espacio Ocupado");
			}
			if (!(Propetario.TienesLasTecnologias (LasTecnologiasNecesarias))){
				throw new NoSePuedeCrear("No Tienes las tecnologias");
			}
			
			return true;
		}

		public void Creame( FichaDeJugador Nueva) {
		Propetario.GastaRecursos (Coste);			
		Propetario.NewFicha(Nueva);
		Mapa.NewFichaTerreste(Lugar, Nueva);
		}
			
		public  ExtrategiaFicha PasarTurno() { //voy a tener que ahcer hjas solo para variar esta funcion. T.T
			return this;
		}
	}

package ExtrategiasFicha.ExtrategiasConstruccion;

import Errores.NoSePuedeCrear;
import ExtrategiasFicha.ExtrategiaFicha;

import Ficha.Ficha;
import Ficha.FichaTerrestre;
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

        @Override
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

        @Override
        public void Creame(Ficha Nueva) {
            Creame((FichaTerrestre) Nueva);
        }

		public void Creame(FichaTerrestre Nueva) {
			Propetario.GastaRecursos(Coste);
			Propetario.NewFicha(Nueva);
			Mapa.NewFichaTerreste(Lugar, Nueva);
		}

        @Override
		public  ExtrategiaFicha PasarTurno() { //voy a tener que ahcer hjas solo para variar esta funcion. T.T
			return this;
		}
	}

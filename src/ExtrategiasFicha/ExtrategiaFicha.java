package ExtrategiasFicha;

import Errores.NoSePuedeCrear;
import Ficha.FichaDeJugador;



public abstract class ExtrategiaFicha {
	
	public boolean MePuedeCrear() throws NoSePuedeCrear {
		return false;
	}
	
	public void Creame( FichaDeJugador Nueva) {
	}

	public ExtrategiaFicha PasarTurno() {
		return this;
	}

	public void Morir(FichaDeJugador ficha) {
		
	}

	
}

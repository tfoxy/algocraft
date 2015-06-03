package ExtrategiasFicha;

import Errores.NoSePuedeCrear;
import Ficha.Ficha;



public abstract class ExtrategiaFicha {
	
	public boolean MePuedeCrear() throws NoSePuedeCrear {
		return false;
	}
	
	public void Creame( Ficha Nueva) {
	}

	public ExtrategiaFicha PasarTurno() {
		return this;
	}

	public void Morir(Ficha ficha) {
		
	}

	
}

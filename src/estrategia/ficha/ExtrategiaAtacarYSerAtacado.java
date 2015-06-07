package estrategia.ficha;

import tablero.Coordenada;
import ficha.FichaAerea;
import ficha.FichaDeJugador;
import ficha.FichaTerrestre;

public class ExtrategiaAtacarYSerAtacado {
	
	int alcanse;
	
	public void serAtacado ( int danio, FichaDeJugador defensor){
		defensor.getbarras().sufrirDaño(danio, defensor);
	}
	
	public boolean atacado ( FichaDeJugador agresor, FichaTerrestre defensor){
		
		int alcanse = agresor.getRangoDeAtaqueTierra();
		if (this.puedoAtacar(agresor,defensor)){
		this.serAtacado (defensor.getAtaqueTierra(), defensor);
		return true;
		}
		return false;
	}

	public boolean atacado ( FichaDeJugador agresor, FichaAerea defensor){
		
		int alcanse = agresor.getRangoDeAtaqueAire();
		if (this.puedoAtacar(agresor,defensor)){
		this.serAtacado (defensor.getAtaqueTierra(), defensor);
		return true;
		}
		return false;
	}
	//se puede ahoraar unas 3 lineas mas de codigo... pero ya qudaria confuso.
	
	
	private boolean puedoAtacar(FichaDeJugador agresor, FichaDeJugador defensor) {
		
		Coordenada posicionAgresor = agresor.getCoordenada();
		Coordenada posicionDefensor = defensor.getCoordenada();
		
		if (alcanse >= posicionAgresor.distanciaAObjetivo(posicionDefensor) ){
			return true;
			}
		return false;
	}
}

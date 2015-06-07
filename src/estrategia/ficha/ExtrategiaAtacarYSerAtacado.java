package estrategia.ficha;

import tablero.Coordenada;
import ficha.FichaDeJugador;


//Faltan polimorfismo y Text
public class ExtrategiaAtacarYSerAtacado {
	
	public void serAtacado ( int danio, FichaDeJugador defensor){
		defensor.getbarras().sufrirDaño(danio, defensor);
	}
	
	public boolean atacado ( FichaDeJugador agresor, FichaDeJugador defensor){
		
		if (this.puedoAtacar(agresor,defensor)){
		this.serAtacado (defensor.getAtaqueTierra(), defensor);
		return true;
		}
		return false;
	}

	private boolean puedoAtacar(FichaDeJugador agresor, FichaDeJugador defensor) {
		
		int alcanse = agresor.getRangoDeAtaqueTierra();
		Coordenada posicionAgresor = agresor.getCoordenada();
		Coordenada posicionDefensor = defensor.getCoordenada();
		
		if (alcanse >= posicionAgresor.distanciaAObjetivo(posicionDefensor) ){
			return true;
			}
		return false;
	}
}

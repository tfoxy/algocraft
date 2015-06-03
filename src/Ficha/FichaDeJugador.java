package Ficha;

import Jugador.Recursos;
import Jugador.TablaJugador;


public abstract class FichaDeJugador implements Ficha {

	protected TablaJugador Propetario;
	
	public boolean EstasVacia() {
		return false;
	}
	
	protected boolean HayRecursosSuficientesParaCrearme(Recursos coste) { //Puede que se balla esta funcion//
		return Propetario.TengoSuficientesRecursos (coste);

	}
	public String HeresUnRecurso(){
		return "no";
	}

}

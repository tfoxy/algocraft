package Ficha;

import Jugador.Recursos;
import Jugador.TablaJugador;


public abstract class FichaAbstracta implements Ficha {

	protected TablaJugador Propetario;

	public FichaAbstracta(TablaJugador jugador) {
        if (jugador == null) {
            throw new NullPointerException("Jugador es nulo");
        }

        Propetario = jugador;
	}

    @Override
	public boolean EstasVacia() {
		return false;
	}

	protected boolean HayRecursosSuficientesParaCrearme(Recursos coste) { //Puede que se balla esta funcion//
		return Propetario.TengoSuficientesRecursos (coste);
	}

}

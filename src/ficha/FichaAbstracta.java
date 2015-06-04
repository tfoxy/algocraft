package ficha;

import jugador.Recursos;
import jugador.TablaJugador;


public abstract class FichaAbstracta implements Ficha {

    private TablaJugador propietario;

    public FichaAbstracta(TablaJugador jugador) {
        if (jugador == null) {
            throw new NullPointerException("Jugador es nulo");
        }

        propietario = jugador;
    }

    protected TablaJugador getPropietario() {
        return propietario;
    }

    @Override
    public boolean estaVacia() {
        return false;
    }

    //Puede que se vaya esta funcion//
    protected boolean hayRecursosSuficientesParaCrearme(Recursos coste) {
        return propietario.tengoSuficientesRecursos(coste);
    }

}

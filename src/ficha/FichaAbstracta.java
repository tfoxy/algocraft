package ficha;

import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;


public abstract class FichaAbstracta implements Ficha {

    private TablaJugador propietario;
    private Tablero tablero;
    private Coordenada coordenada;

    public FichaAbstracta(TablaJugador jugador) {
        if (jugador == null) {
            throw new NullPointerException("Jugador es nulo");
        }

        propietario = jugador;
    }

    @Override
    public TablaJugador getPropietario() {
        return propietario;
    }

    @Override
    public Tablero getTablero() {
        return tablero;
    }

    @Override
    public Coordenada getCoordenada() {
        return coordenada;
    }

    @Override
    public boolean estaVacia() {
        return false;
    }

    @Override
     public void muerete() {
        getPropietario().perderFicha(this);
    }

    // TODO refactorizar función hayRecursosSuficientesParaCrearme
    protected boolean hayRecursosSuficientesParaCrearme(Recursos coste) {
        return propietario.tengoSuficientesRecursos(coste);
    }

}

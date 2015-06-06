package ficha;


import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;

public interface Ficha {

    boolean estaVacia();

    void pasarTurno();

    void muerete();

    TablaJugador getPropietario();

    Tablero getTablero();

    Coordenada getCoordenada();

    String getNombre();

}

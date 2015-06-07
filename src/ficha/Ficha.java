package ficha;

import tablero.Coordenada;//new 6
import tablero.Tablero;

import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;

public interface Ficha {

    boolean estaVacia();

    void pasarTurno();

    void muerete();

    TablaJugador getPropietario();

    Tablero getTablero();

    void setTablero(Tablero tablero);

    Coordenada getCoordenada();

    String getNombre();

    void setCoordenada(Coordenada coordenada);

    int getMovimiento();

    int getMovimientoMaximo();

    int getAtaqueTierra();

    int getAtaqueAire();
}

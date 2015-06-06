package ficha;

import tablero.Coordenada;//new 6
import tablero.Tablero;

public interface Ficha {

    public Coordenada ubicacion = null; //new 6
    public Tablero mapa = null;

    boolean estaVacia();

    void pasarTurno();

    void muerete();

}

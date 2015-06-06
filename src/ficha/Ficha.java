package ficha;

import tablero.Coordenada;//new 6

public interface Ficha {

    Coordenada ubicacion = null; //new 6

    boolean estaVacia();

    void pasarTurno();

    void muerete();

}

package ficha;

import tablero.CasillaDeTablero;//new 6

public interface Ficha {

    CasillaDeTablero ubicacion = null; //new 6

    boolean estaVacia();

    void pasarTurno();

    void muerete();

}

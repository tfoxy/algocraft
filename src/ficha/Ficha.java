package ficha;

import tablero.Coordenada;//new 6
import tablero.Tablero;

public interface Ficha {

    Coordenada ubicacion = null; //new 6
	Tablero mapa = null;

    boolean estaVacia();

    void pasarTurno();

    void muerete();

}

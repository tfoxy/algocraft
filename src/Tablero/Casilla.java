package Tablero;

import Ficha.FichaTerrestre;
import Ficha.FichaAerea;

public interface Casilla {
    FichaTerrestre getFichaTerrestre();

    FichaAerea getFichaAerea();

    void insertar(FichaTerrestre ficha);

    void insertar(FichaAerea ficha);

    void eliminarFichaTerrestre();

    void eliminarFichaAerea();
}

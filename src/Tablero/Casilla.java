package Tablero;

import Ficha.FichaTerrestre;
import Ficha.FichaAerea;

public interface Casilla {
    Terreno getTerreno();

    FichaTerrestre getFichaTerrestre();

    FichaAerea getFichaAerea();

    void modificar(Terreno terreno);

    void insertar(FichaTerrestre ficha);

    void insertar(FichaAerea ficha);

    void eliminarFichaTerrestre();

    void eliminarFichaAerea();
}

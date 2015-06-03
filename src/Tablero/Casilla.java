package Tablero;

import Ficha.Ficha;
import Ficha.FichaAerea;

public interface Casilla {
    Terreno getTerreno();

    Ficha getFichaTerrestre();

    FichaAerea getFichaAerea();

    void modificar(Terreno terreno);

    void insertar(Ficha ficha);

    void insertar(FichaAerea ficha);

    void eliminarFichaTerrestre();

    void eliminarFichaAerea();
}

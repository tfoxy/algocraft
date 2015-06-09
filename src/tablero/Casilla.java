package tablero;

import ficha.FichaTerrestre;
import ficha.FichaAerea;

public interface Casilla {
    FichaTerrestre getFichaTerrestre();

    FichaAerea getFichaAerea();

    void insertar(FichaTerrestre ficha);

    void insertar(FichaAerea ficha);

    void eliminarFichaTerrestre();

    void eliminarFichaAerea();

    Casilla get(Direccion direccion);
}

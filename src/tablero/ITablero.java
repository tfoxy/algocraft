package tablero;

import ficha.Ficha;

public interface ITablero {
    Ficha getFicha(Coordenada3d lugar);

    Ficha getFichaTerrestre(Coordenada lugar);

    Ficha getFichaAerea(Coordenada lugar);

    boolean hayEspacio(Coordenada3d coordenada);

    boolean hayEspacioTerreste(Coordenada lugar);

    boolean hayEspacioAereo(Coordenada lugar);

    void insertar(Ficha ficha);

    void eliminarFichaEn(Coordenada3d lugar);

    int getLongitudX();

    int getLongitudY();
}

package tablero;

import ficha.Ficha;
import juego.Gaia;

public interface ITablero {
    void verificar(Coordenada3d lugar);

    Ficha getFicha(Coordenada3d lugar);

    Ficha getFichaTerrestre(Coordenada lugar);

    Ficha getFichaAerea(Coordenada lugar);

    Ficha getFichaCelestial(Coordenada lugar);

    boolean hayEspacio(Coordenada3d coordenada);

    boolean hayEspacioTerreste(Coordenada lugar);

    boolean hayEspacioAereo(Coordenada lugar);

    void insertar(Ficha ficha);

    void eliminarFichaEn(Coordenada3d lugar);

    int getLongitudX();

    int getLongitudY();

    Gaia gaia();
}

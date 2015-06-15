package tablero;

import java.util.HashMap;
import java.util.Map;

import error.FichaSobreOtraFichaException;
import error.PosicionFueraDeLimiteException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;

public class Tablero {

    private static final Map<Integer, Ficha> FICHAS_VACIAS;

    static {
        FICHAS_VACIAS = new HashMap<>();
        FICHAS_VACIAS.put(Altura.TIERRA, new FichaTerrestre());
        FICHAS_VACIAS.put(Altura.AIRE, new FichaAerea());
    }


    private int longitudX;
    private int longitudY;
    private Map<Coordenada3d, Ficha> fichas = new HashMap<>();


    public Tablero(int x, int y) {
        longitudX = x;
        longitudY = y;
    }


    private void verificar(Coordenada3d lugar) {
        int x = lugar.getX();
        int y = lugar.getY();
        int z = lugar.getZ();

        if (x < 1 || x > longitudX
                || y < 1 || y > longitudY
                || z < Altura.TIERRA || z > Altura.AIRE) {
            throw new PosicionFueraDeLimiteException();
        }
    }


    public Ficha getFicha(Coordenada3d lugar) {
        verificar(lugar);

        Ficha ficha = fichas.get(lugar);

        if (ficha == null) {
            ficha = FICHAS_VACIAS.get(lugar.getZ());
        }

        return ficha;
    }


    public Ficha getFichaTerrestre(Coordenada lugar) {
        return getFicha(new Coordenada3d(lugar, Altura.TIERRA));
    }


    public Ficha getFichaAerea(Coordenada lugar) {
        return getFicha(new Coordenada3d(lugar, Altura.AIRE));
    }


    public boolean hayEspacio(Coordenada3d coordenada) {
        return getFicha(coordenada).estoyVacio();
    }


    public boolean hayEspacioTerreste(Coordenada lugar) {
        return hayEspacio(new Coordenada3d(lugar, Altura.TIERRA));
    }

    public boolean hayEspacioArreo(Coordenada lugar) { //new 6
        return hayEspacio(new Coordenada3d(lugar, Altura.AIRE));
    }


    public void insertar(Coordenada lugar, Ficha ficha) {
        Coordenada3d coord3d = new Coordenada3d(lugar, ficha.altura());
        Ficha fichaEnLugar = getFicha(coord3d);

        if (!fichaEnLugar.estoyVacio()) {
            throw new FichaSobreOtraFichaException();
        }

        fichas.put(coord3d, ficha);

        ficha.coordenada(lugar);
        ficha.tablero(this);
    }


    public void eliminarFicha(Coordenada3d lugar) {
        fichas.remove(lugar);
    }


    public void eliminarFichaTerrestre(Coordenada lugar) {
        eliminarFicha(new Coordenada3d(lugar, Altura.TIERRA));
    }


    public void eliminarFichaAerea(Coordenada lugar) {
        eliminarFicha(new Coordenada3d(lugar, Altura.AIRE));
    }


    public Casilla getCasilla() {
        return null;
    }


    public int getLongitudX() {
        return longitudX;
    }


    public int getLongitudY() {
        return longitudY;
    }
}

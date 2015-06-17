package tablero;

import java.util.HashMap;
import java.util.Map;

import error.PosicionFueraDeLimiteException;
import ficha.Ficha;
import ficha.natural.terreno.TerrenoAire;
import ficha.natural.terreno.TerrenoTierra;
import juego.Gaia;

public class Tablero implements ITablero {

    private final int longitudX;
    private final int longitudY;
    private final Map<Coordenada3d, Ficha> fichas = new HashMap<>();
    private final Map<Integer, Ficha> fichasVacias = new HashMap<>();


    public Tablero(int x, int y) {
        this(x, y, new Gaia());
    }


    public Tablero(int x, int y, Gaia gaia) {
        longitudX = x;
        longitudY = y;
        inicializarFichasVacias(gaia);
    }


    private void inicializarFichasVacias(Gaia gaia) {
        Ficha tierra = new TerrenoTierra();
        tierra.propietario(gaia);
        fichasVacias.put(Altura.TIERRA, tierra);

        Ficha aire = new TerrenoAire();
        aire.propietario(gaia);
        fichasVacias.put(Altura.AIRE, aire);
    }


    private void verificar(Coordenada3d lugar) { //se inicia a contar por 0
        int x = lugar.getX();
        int y = lugar.getY();
        int z = lugar.getZ();

        if (x < 0 || x > longitudX
                || y < 0 || y > longitudY
                || z < Altura.TIERRA || z > Altura.AIRE) {
            throw new PosicionFueraDeLimiteException();
        }
    }


    @Override
    public Ficha getFicha(Coordenada3d lugar) {
        verificar(lugar);

        Ficha ficha = fichas.get(lugar);

        if (ficha == null) {
            ficha = fichasVacias.get(lugar.getZ());
        }

        return ficha;
    }


    @Override
    public Ficha getFichaTerrestre(Coordenada lugar) {
        return getFicha(new Coordenada3d(lugar, Altura.TIERRA));
    }


    @Override
    public Ficha getFichaAerea(Coordenada lugar) {
        return getFicha(new Coordenada3d(lugar, Altura.AIRE));
    }


    @Override
    public boolean hayEspacio(Coordenada3d coordenada) {
        return getFicha(coordenada).estoyVacio();
    }


    @Override
    public boolean hayEspacioTerreste(Coordenada lugar) {
        return hayEspacio(new Coordenada3d(lugar, Altura.TIERRA));
    }

    @Override
    public boolean hayEspacioAereo(Coordenada lugar) { //new 6
        return hayEspacio(new Coordenada3d(lugar, Altura.AIRE));
    }


    @Override
    public void insertar(Ficha ficha) {
        verificar(ficha.coordenada());

        fichas.put(ficha.coordenada(), ficha);
    }


    @Override
    public void eliminarFichaEn(Coordenada3d lugar) {
        fichas.remove(lugar);
    }


    @Override
    public int getLongitudX() {
        return longitudX;
    }


    @Override
    public int getLongitudY() {
        return longitudY;
    }
}

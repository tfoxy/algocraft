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
    private final Gaia gaia;

    public Tablero(int x, int y) {
        this(x, y, new Gaia());
    }


    public Tablero(int x, int y, Gaia gaia) {
        longitudX = x;
        longitudY = y;
        this.gaia = gaia;
    }


    private Ficha getFichaVacia(Coordenada3d lugar) {
        Ficha ficha = lugar.z == Altura.TIERRA
                ? new TerrenoTierra()
                : new TerrenoAire();
        ficha.setBasico(gaia, this, lugar);
        return ficha;
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
            ficha = getFichaVacia(lugar);
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

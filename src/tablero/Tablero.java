package tablero;

import java.util.HashMap;
import java.util.Map;

import error.PosicionFueraDeLimiteException;
import ficha.Ficha;
import ficha.natural.terreno.TerrenoAire;
import ficha.natural.terreno.TerrenoCielo;
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
        final Ficha ficha;
        switch (lugar.z) {
            case Altura.TIERRA: ficha = new TerrenoTierra(); break;
            case Altura.AIRE: ficha = new TerrenoAire(); break;
            case Altura.CIELO: ficha = new TerrenoCielo(); break;
            default: throw new PosicionFueraDeLimiteException(lugar);
        }
        ficha.setBasico(gaia, this, lugar);
        return ficha;
    }


    @Override
    public void verificarEnArea(Coordenada lugar) {
        int x = lugar.x;
        int y = lugar.y;

        if (x < 1 || x > longitudX
                || y < 1 || y > longitudY) {
            throw new PosicionFueraDeLimiteException(lugar);
        }
    }


    @Override
    public void verificar(Coordenada3d lugar) {
        verificarEnArea(lugar);

        int z = lugar.getZ();

        if (z < Altura.MINIMA || z > Altura.MAXIMA) {
            throw new PosicionFueraDeLimiteException(lugar);
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
    public Ficha getFichaCelestial(Coordenada lugar) {
        return getFicha(new Coordenada3d(lugar, Altura.CIELO));
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

    @Override
    public Gaia gaia() {
        return gaia;
    }
}

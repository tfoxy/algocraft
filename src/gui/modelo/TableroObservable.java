package gui.modelo;

import ficha.Ficha;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.ITablero;

public class TableroObservable extends Observable<Ficha> implements ITablero {

    private final ITablero tablero;

    public TableroObservable(ITablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void verificar(Coordenada3d lugar) {
        tablero.verificar(lugar);
    }

    @Override
    public Ficha getFicha(Coordenada3d lugar) {
        return tablero.getFicha(lugar);
    }

    @Override
    public Ficha getFichaTerrestre(Coordenada lugar) {
        return tablero.getFichaTerrestre(lugar);
    }

    @Override
    public Ficha getFichaAerea(Coordenada lugar) {
        return tablero.getFichaAerea(lugar);
    }

    @Override
    public Ficha getFichaCelestial(Coordenada lugar) {
        return tablero.getFichaCelestial(lugar);
    }

    @Override
    public boolean hayEspacio(Coordenada3d coordenada) {
        return tablero.hayEspacio(coordenada);
    }

    @Override
    public boolean hayEspacioTerreste(Coordenada lugar) {
        return tablero.hayEspacioTerreste(lugar);
    }

    @Override
    public boolean hayEspacioAereo(Coordenada lugar) {
        return tablero.hayEspacioAereo(lugar);
    }

    @Override
    public void insertar(Ficha ficha) {
        tablero.insertar(ficha);

        this.notifyObservers(ficha);
    }

    @Override
    public void eliminarFichaEn(Coordenada3d lugar) {
        tablero.eliminarFichaEn(lugar);

        this.notifyObservers(tablero.getFicha(lugar));
    }

    @Override
    public int getLongitudX() {
        return tablero.getLongitudX();
    }

    @Override
    public int getLongitudY() {
        return tablero.getLongitudY();
    }
}

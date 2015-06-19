package gui.vista;

import ficha.Ficha;
import gui.modelo.Observable;
import gui.modelo.Observer;
import tablero.Coordenada;

public abstract class CasillaObserver implements Observer<Ficha> {

    private Coordenada coordenada;

    @Override
    public void update(Observable o, Ficha ficha) {
        if (ficha.coordenada().proyeccion().equals(coordenada))
            updateVista(ficha);
    }

    public CasillaObserver(Coordenada coordenada) {
        this.coordenada = coordenada;

    }

    protected abstract void updateVista(Ficha ficha);

}

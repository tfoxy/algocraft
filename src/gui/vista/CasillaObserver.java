package gui.vista;

import ficha.Ficha;
import gui.modelo.TableroObservable;
import tablero.Coordenada;
import tablero.Coordenada3d;

import java.util.Observable;
import java.util.Observer;

public abstract class CasillaObserver implements Observer {

    private static final long serialVersionUID = -973667959098244571L;

    private Coordenada coordenada;

    @Override
    public void update(Observable o, Object arg) {
        Ficha ficha = (Ficha) arg;

        if (ficha.coordenada().proyeccion().equals(coordenada))
            updateVista(ficha);
    }

    public CasillaObserver(Coordenada coordenada) {
        this.coordenada = coordenada;
        //addMouseListener(new ControladorMouse(mapa, this));

    }

    protected abstract void updateVista(Ficha ficha);

}

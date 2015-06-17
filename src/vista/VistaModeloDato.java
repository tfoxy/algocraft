package vista;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controladores.ControladorMouse;

import auxiliares.ModeloObservable;
import auxiliares.Posicion;



public abstract class VistaModeloDato extends JPanel implements Observer {

    private static final long serialVersionUID = -973667959098244571L;

    protected ModeloObservable modelo;

    public Posicion posicion;

    public void update(Observable o, Object arg) {
            updateVista();
    }

    public VistaModeloDato(Posicion posicion, ModeloObservable modelo) {

        this.posicion = posicion;
        this.modelo = modelo;
        this.modelo.addObserver(this);
        addMouseListener(new ControladorMouse(modelo, this));

    }

    protected abstract void updateVista();

}

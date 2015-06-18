package gui.controlador;

import java.util.Observable;

public class ObservableEvent extends Observable {
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}

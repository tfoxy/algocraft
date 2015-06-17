package gui.modelo;

import java.util.Observable;

public class ObservableElement<E> extends Observable {

    private E e;

    public ObservableElement(E e) {
        this.e = e;
    }

    public E get() {
        return e;
    }

    public void set(E e) {
        this.e = e;

        setChanged();
        this.notifyObservers(e);
    }

}

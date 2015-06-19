package gui.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class ElementObservable<E> {

    private E e;

    private List<ElementObserver<E>> observers = new LinkedList<>();

    public ElementObservable(E e) {
        this.e = e;
    }

    public E get() {
        return e;
    }

    public void set(E e) {
        this.e = e;
    }

    public void setAndNotify(E e) {
        if (this.e == e)
            return;

        this.updateAndNotify(e);
    }

    public void updateAndNotify(E e) {
        E prevElement = this.e;
        this.e = e;

        this.notifyObservers(prevElement);
    }

    public void addObserver(ElementObserver<E> o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers(E e) {
        for (ElementObserver<E> o: observers) {
            o.update(this, e);
        }
    }
}

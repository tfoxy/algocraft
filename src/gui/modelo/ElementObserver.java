package gui.modelo;

public interface ElementObserver<E> {
    void update(ElementObservable<E> o, E prevElement);
}

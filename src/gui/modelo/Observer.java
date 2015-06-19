package gui.modelo;

public interface Observer<ObservedType> {
    void update(Observable<ObservedType> object, ObservedType data);
}

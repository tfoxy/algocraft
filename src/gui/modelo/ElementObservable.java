package gui.modelo;

public class ElementObservable<ObservedType> extends Observable<ObservedType> {

    private ObservedType observedType;

    public ElementObservable(ObservedType observedType) {
        this.observedType = observedType;
    }

    public ObservedType get() {
        return observedType;
    }

    public void set(ObservedType observedType) {
        this.observedType = observedType;
    }

    public void setAndNotify(ObservedType observedType) {
        if (this.observedType == observedType)
            return;

        this.updateAndNotify(observedType);
    }

    public void updateAndNotify(ObservedType observedType) {
        ObservedType prevElement = this.observedType;
        this.observedType = observedType;

        this.notifyObservers(prevElement);
    }
}

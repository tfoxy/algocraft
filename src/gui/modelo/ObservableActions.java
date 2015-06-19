package gui.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObservableActions<K, V> {

    private final Map<K, Observable<V>> observableMap;

    public ObservableActions(Set<K> actions) {
        this.observableMap = new HashMap<>();

        for (K action: actions) {
            this.observableMap.put(action, new Observable<V>());
        }
    }

    protected ObservableActions(Map<K, Observable<V>> observableMap) {
        this.observableMap = observableMap;
    }

    private Observable<V> getObservable(K action) {
        final Observable<V> observable = observableMap.get(action);

        if (observable == null) {
            throw new IllegalArgumentException("Action does not exists");
        }

        return observable;
    }

    public void on(K action, Observer<V> observer) {
        getObservable(action).addObserver(observer);
    }

    public void on(Set<K> actions, Observer<V> observer) {
        for (K action: actions) {
            this.on(action, observer);
        }
    }

    public void observe(Observer<V> observer) {
        for (Observable<V> observable: observableMap.values()) {
            observable.addObserver(observer);
        }
    }

    public void notify(K action, V data) {
        getObservable(action).notifyObservers(data);
    }

    public void delete(K action, Observer observer) {
        getObservable(action).deleteObserver(observer);
    }
}

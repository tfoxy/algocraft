package gui.modelo;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class ObservableEnumActions<K extends Enum<K>, V> extends ObservableActions<K, V> {
    public ObservableEnumActions(Class<K> kClass) {
        this(kClass, EnumSet.allOf(kClass));
    }

    public ObservableEnumActions(Class<K> kClass, Set<K> actionSet) {
        super(ObservableEnumActions.<K, V>createMap(kClass, actionSet));
    }

    private static <K extends Enum<K>, V> Map<K, Observable<V>>
    createMap(Class<K> kClass, Set<K> actionSet) {
        Map<K, Observable<V>> observableMap = new EnumMap<>(kClass);

        for (K action: actionSet) {
            observableMap.put(action, new Observable<V>());
        }

        return observableMap;
    }
}

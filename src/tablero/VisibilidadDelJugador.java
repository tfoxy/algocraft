package tablero;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;


public class VisibilidadDelJugador {
    private ObservableSet<Coordenada> visibilidad =
            FXCollections.observableSet(new HashSet<Coordenada>());

    public void verDesde(Coordenada coordenada, int vision) {
        Set<Coordenada> set = CoordenadaUtil.areaDeCoordenadas(coordenada, vision);
        visibilidad.addAll(set);
    }

    public boolean puedeVer(Coordenada coordenada) {
        return visibilidad.contains(coordenada);
    }

    public void addListener(SetChangeListener<? super Coordenada> listener) {
        visibilidad.addListener(listener);
    }

    public void removeListener(SetChangeListener<? super Coordenada> listener) {
        visibilidad.removeListener(listener);
    }

}

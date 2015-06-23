package gui.modelo;

import ficha.Ficha;
import magia.Magia;
import tablero.Coordenada3d;

import java.util.Collections;
import java.util.List;

/**
 * Deseleccionar al cambiar a una acción que no sea AccionEnGrilla.EMISION_DE_MAGIA
 * o al comenzar el turno del jugador.
 */
public class MagiasDisponibles extends ListInstanceModel<Magia> {
    private Ficha caster;
    private Magia magiaSeleccionada;
    private final FichaObjetivo fichaObjetivo;

    public MagiasDisponibles(FichaObjetivo fichaObjetivo) {
        this.caster = null;
        this.magiaSeleccionada = null;
        this.fichaObjetivo = fichaObjetivo;

        fichaObjetivo.accionObservable().addObserver(new CambiarAccionObserver());
    }

    public void aplicarEn(Coordenada3d coordenada3d) {
        magiaSeleccionada.realizar(caster, coordenada3d);

        deseleccionar();
    }

    public Ficha caster() {
        return caster;
    }

    public Magia magiaSeleccionada() {
        return magiaSeleccionada;
    }

    @Override
    public List<Magia> list() {
        if (caster() == null)
            return Collections.emptyList();
        else
            return caster().magias();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        magiaSeleccionada = (Magia) anItem;

        fireContentsChanged(this, -1, -1);

        if (magiaSeleccionada != null) {
            this.fichaObjetivo.cambiarAccion(AccionEnGrilla.EMISION_DE_MAGIA);
        } else if (this.fichaObjetivo.accion().equals(AccionEnGrilla.EMISION_DE_MAGIA)) {
            this.fichaObjetivo.retornarAccionPorDefecto();
        }
    }

    @Override
    public Magia getSelectedItem() {
        return magiaSeleccionada;
    }

    void cambiarCaster(Ficha nuevoCaster) {
        int antiguaCantidad = list().size();

        this.caster = nuevoCaster;

        int nuevaCantidad = list().size();

        fireListChanged(antiguaCantidad, nuevaCantidad);

        deseleccionar();
    }

    private void deseleccionar() {
        setSelectedItem(null);
    }

    private class CambiarAccionObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            if (!data.accion().equals(AccionEnGrilla.EMISION_DE_MAGIA))
                deseleccionar();
        }
    }

    private class ComenzarTurnoObserver implements Observer<JugadorDeTurno> {
        @Override
        public void update(Observable<JugadorDeTurno> object, JugadorDeTurno data) {
            deseleccionar();
        }
    }
}

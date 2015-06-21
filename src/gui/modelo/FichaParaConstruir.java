package gui.modelo;

import ficha.Ficha;
import ficha.Fichas;
import tablero.Coordenada;
import tablero.ITablero;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.List;

/**
 * Deseleccionar al cambiar a una acción que no sea AccionEnGrilla.CONSTRUCCION
 * o al comenzar el turno del jugador.
 */
public class FichaParaConstruir extends AbstractListModel<Ficha> implements ComboBoxModel<Ficha> {

    private Ficha ficha;
    private final FichaObjetivo fichaObjetivo;
    private final JugadorDeTurno jugadorDeTurno;

    public FichaParaConstruir(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.ficha = null;
        this.fichaObjetivo = fichaObjetivo;
        this.jugadorDeTurno = jugadorDeTurno;

        fichaObjetivo.accionObservable().addObserver(new CambiarAccionObserver());
        jugadorDeTurno.comenzarTurnoObservable().addObserver(new ComenzarTurnoObserver());
    }

    private void deseleccionar() {
        setSelectedItem(null);
    }

    public void ubicarEn(Coordenada coordenada) {
        ITablero mapa = jugadorDeTurno.juego().tablero();

        Ficha ficha = Fichas.newInstance(this.ficha).enConstruccion();
        ficha.setBasico(jugadorDeTurno.jugador(), mapa, coordenada);
        ficha.ponerEnJuego();

        deseleccionar();
    }

    public List<Ficha> listaDeFichasPosibles() {
        return jugadorDeTurno.jugador().raza().listaDeFichas();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.ficha = (Ficha) anItem;

        fireContentsChanged(this, -1, -1);

        if (ficha != null) {
            this.fichaObjetivo.cambiarAccion(AccionEnGrilla.CONSTRUCCION);
        } else if (this.fichaObjetivo.accion().equals(AccionEnGrilla.CONSTRUCCION)) {
            this.fichaObjetivo.retornarAccionPorDefecto();
        }
    }

    @Override
    public Ficha getSelectedItem() {
        return ficha;
    }

    @Override
    public int getSize() {
        return listaDeFichasPosibles().size();
    }

    @Override
    public Ficha getElementAt(int index) {
        return listaDeFichasPosibles().get(index);
    }

    private class CambiarAccionObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            if (!data.accion().equals(AccionEnGrilla.CONSTRUCCION))
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

package gui.controlador;

import error.JuegoException;
import ficha.Ficha;
import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;

import javax.swing.JComboBox;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

public class ControladorJugador {

    private final JugadorDeTurno jugadorDeTurno;
    private JuegoLogger juegoLogger = JuegoLogger.EMPTY;

    public ControladorJugador(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.jugadorDeTurno = jugadorDeTurno;

        fichaObjetivo.fichaObservables().on(AccionEnGrilla.CONSTRUCCION,
                new UbicarFichaParaConstruirObserver());
    }

    public void setJuegoLogger(JuegoLogger juegoLogger) {
        this.juegoLogger = juegoLogger;
    }

    private class UbicarFichaParaConstruirObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            try {
                jugadorDeTurno.fichaParaConstruir().ubicarEn(data.ficha().coordenada());
            } catch (JuegoException e) {
                juegoLogger.log(e);
            }
        }
    }

    public JugadorDeTurno jugadorDeTurno() {
        return this.jugadorDeTurno;
    }

    private class TerminarTurnoListener extends AnyEventListener {

        private TerminarTurnoListener() {
            // noop
        }

        @Override
        public void eventOcurred(AWTEvent e) {
            jugadorDeTurno.terminarTurno();
        }
    }

    public AnyEventListener getTerminarTurnoListener() {
        return new TerminarTurnoListener();
    }

}

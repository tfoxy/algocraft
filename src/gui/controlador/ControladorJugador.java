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

    public ControladorJugador(JugadorDeTurno jugadorDeTurno) {
        this.jugadorDeTurno = jugadorDeTurno;
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

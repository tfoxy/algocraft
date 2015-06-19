package gui.vista;

import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardMap;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;
import juego.Jugador;
import tablero.Direccion;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;

public class JugadorView extends JPanel {

    private Jugador jugador;

    private JLabel nombreLabel = new JLabel();
    private JButton botonTerminarTurno = new JButton("Terminar turno");

    public JugadorView(ControladorJugador control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(nombreLabel);
        add(botonTerminarTurno);

        addActionListeners(control);

        botonTerminarTurno.setMnemonic(KeyEvent.VK_T);
        botonTerminarTurno.setToolTipText("ENTER");

        cambiarJugador(control.jugadorDeTurno().jugador());

        control.jugadorDeTurno().comenzarTurnoObservable().addObserver(new ComenzarTurnoObserver());
    }


    private void addActionListeners(ControladorJugador control) {
        botonTerminarTurno.addActionListener(control.getTerminarTurnoListener());

        KeyboardMap keyboardMap = new KeyboardMap(this);
        keyboardMap.put("ENTER", control.getTerminarTurnoListener());
    }


    private class ComenzarTurnoObserver implements Observer<JugadorDeTurno> {
        @Override
        public void update(Observable<JugadorDeTurno> o, JugadorDeTurno jugadorDeTurno) {
            cambiarJugador(jugadorDeTurno.jugador());
        }
    }


    private void cambiarJugador(Jugador jugador) {
        this.jugador = jugador;
        actulizarJugador();
    }

    private void actulizarJugador() {
        nombreLabel.setText(jugador.nombre());
    }

}

package gui.vista;

import gui.controlador.ControladorJugador;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import juego.Jugador;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JugadorView extends JPanel {

    private Jugador jugador;

    private JLabel nombreLabel = new JLabel();
    private JButton botonTerminarTurno = new JButton("Terminar turno");

    public JugadorView(ControladorJugador control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(botonTerminarTurno);

        botonTerminarTurno.addActionListener(control.getTerminarTurnoListener());

        control.observarCambioDeJugador(new ElementObserver<Jugador>() {
            @Override
            public void update(ElementObservable<Jugador> o, Jugador prevElement) {
                cambiarJugador(o.get());
            }
        });
    }


    private void cambiarJugador(Jugador jugador) {
        this.jugador = jugador;
        actulizarJugador();
    }

    private void actulizarJugador() {
        nombreLabel.setText(jugador.nombre());
    }

}

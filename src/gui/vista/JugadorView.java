package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardMap;
import gui.modelo.FichaObjetivo;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;
import juego.Jugador;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class JugadorView extends JPanel {

    private Jugador jugador;

    private JLabel nombreLabel = new JLabel();
    private JComboBox<Ficha> fichaParaConstruirCombobox;
    private JButton botonTerminarTurno = new JButton("Terminar turno");

    public JugadorView(ControladorJugador control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JugadorDeTurno jugadorDeTurno = control.jugadorDeTurno();

        fichaParaConstruirCombobox =
                new JComboBox<>(jugadorDeTurno.fichaParaConstruir());

        add(nombreLabel);
        add(fichaParaConstruirCombobox);
        add(botonTerminarTurno);

        addActionListeners(control);

        fichaParaConstruirCombobox.setRenderer(new FichaParaConstruirRenderer());

        botonTerminarTurno.setMnemonic(KeyEvent.VK_T);
        botonTerminarTurno.setToolTipText("ENTER");

        cambiarJugador(jugadorDeTurno.jugador());

        jugadorDeTurno.comenzarTurnoObservable().addObserver(new ComenzarTurnoObserver());
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

    private static class FichaParaConstruirRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Ficha ficha = (Ficha) value;
            setText(ficha == null ? "Construir..." : ficha.nombre() + " " + ficha.coste());
            return this;
        }
    }


    private void resetearFichaParaConstruirCombobox() {
        this.fichaParaConstruirCombobox.setSelectedIndex(0);
        this.fichaParaConstruirCombobox.repaint();
    }


    private void cambiarJugador(Jugador jugador) {
        this.jugador = jugador;
        fichaParaConstruirCombobox.setMaximumRowCount(jugador.raza().listaDeFichas().size());
        actulizarJugador();
    }

    private void actulizarJugador() {
        nombreLabel.setText(jugador.nombre());

    }

}

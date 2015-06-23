package gui.vista;

import ficha.Ficha;
import gui.controlador.ControlFinDelJuego;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardMap;
import gui.modelo.FichaParaConstruir;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;
import juego.Jugador;
import juego.RecursosDeJugador;

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
    private JLabel recursosLabel = new JLabel();
    private JComboBox<Ficha> fichaParaConstruirCombobox;
    private JButton botonTerminarTurno = new JButton("Terminar turno");


    public JugadorView(ControladorJugador control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JugadorDeTurno jugadorDeTurno = control.jugadorDeTurno();

        fichaParaConstruirCombobox =
                new JComboBox<>(jugadorDeTurno.fichaParaConstruir());

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(recursosLabel);

        add(panelStats);
        add(fichaParaConstruirCombobox);
        add(botonTerminarTurno);

        addActionListeners(control);

        fichaParaConstruirCombobox.setRenderer(new FichaParaConstruirRenderer());

        botonTerminarTurno.setMnemonic(KeyEvent.VK_T);
        botonTerminarTurno.setToolTipText("ENTER");

        cambiarJugador(jugadorDeTurno.jugador());

        jugadorDeTurno.fichaParaConstruir().getConstruccionObservable().addObserver(new ConstruccionObserver());
        jugadorDeTurno.comenzarTurnoObservable().addObserver(new ComenzarTurnoObserver());
        jugadorDeTurno.jugadorGanoObservable().addObserver(new JugadorGanoObservable());
    }

    private class JugadorGanoObservable implements Observer<JugadorDeTurno> {

        @Override
        public void update(Observable<JugadorDeTurno> object,
                           JugadorDeTurno data) {
            ControlFinDelJuego control = new ControlFinDelJuego();
            new VistaGanador(jugador, control);
        }

    }

    private void addActionListeners(ControladorJugador control) {
        botonTerminarTurno.addActionListener(control.getTerminarTurnoListener());

        KeyboardMap keyboardMap = new KeyboardMap(this);
        keyboardMap.put("ENTER", control.getTerminarTurnoListener());
    }

    private class ConstruccionObserver implements Observer<FichaParaConstruir> {

        @Override
        public void update(Observable<FichaParaConstruir> object,
                           FichaParaConstruir data) {
            actulizarRecursos();
        }

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


    private void cambiarJugador(Jugador jugador) {
        this.jugador = jugador;
        fichaParaConstruirCombobox.setMaximumRowCount(jugador.raza().listaDeFichas().size());
        actulizarJugador();
        actulizarRecursos();
    }

    private void actulizarJugador() {
        nombreLabel.setText(jugador.nombre());
    }

    private void actulizarRecursos() {
        RecursosDeJugador recursos = jugador.recursos();
        String str = String.format("M:%d G:%d P:%d/%d",
                recursos.mineral(),
                recursos.gas(),
                recursos.poblacion().actual(),
                recursos.poblacion().posible()
                );
        recursosLabel.setText(str);
    }

}

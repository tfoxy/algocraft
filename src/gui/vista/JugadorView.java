package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardMap;
import gui.modelo.FichaObjetivo;
import gui.modelo.FichaParaConstruir;
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
    
    
    private final JLabel gasLabel = new JLabel();
    private final JLabel cristalLabel = new JLabel();
    private final JLabel poblacionLabel = new JLabel();
    private final JLabel poblacionMaximoLabel = new JLabel();


    public JugadorView(ControladorJugador control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); //si se rompio fue esta linea eprdon.

        JugadorDeTurno jugadorDeTurno = control.jugadorDeTurno();

        fichaParaConstruirCombobox =
                new JComboBox<>(jugadorDeTurno.fichaParaConstruir());

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" Cristal:"));
        panelStats.add(cristalLabel);
        panelStats.add(new JLabel(" Gas:"));
        panelStats.add(gasLabel);
        panelStats.add(new JLabel(" Poblacion:"));
        panelStats.add(poblacionLabel );
        panelStats.add(new JLabel("/"));
        panelStats.add(poblacionMaximoLabel );
        
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


    private void resetearFichaParaConstruirCombobox() {
        this.fichaParaConstruirCombobox.setSelectedIndex(0);
        this.fichaParaConstruirCombobox.repaint();
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
    	gasLabel.setText(jugador.cantidadGas() + "");
        cristalLabel.setText(jugador.cantidadMineral() + "");
        poblacionLabel.setText(jugador.poblcacionActual() + "");
        poblacionMaximoLabel.setText(jugador.poblacionPosible()+ "");
    }
    
}

package vista;

import controladores.ControladorFicha;
import ficha.Ficha;
import gui.controlador.KeyboardMap;
import gui.modelo.FichaSeleccionada;
import gui.modelo.Observable;
import gui.modelo.Observer;
import tablero.Direccion;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class FichaView extends JPanel {

    private Ficha ficha;

    private final JLabel nombreLabel = new JLabel();
    private final JLabel movimientoLabel = new JLabel();
    private final JLabel movimientoMaximoLabel = new JLabel();
    private final JLabel barrasLabel = new JLabel();

    private final JButton botonArriba = new JButton("Arriba");
    private final JButton botonAbajo = new JButton("Abajo");
    private final JButton botonIzquierda = new JButton("Izquierda");
    private final JButton botonDerecha = new JButton("Derecha");
    private final JButton botonAtaque = new JButton("Ataque");
    private final JButton botonCargar = new JButton("Cargar");
    private final JComboBox<Ficha> descargarCombobox;


    public FichaView(ControladorFicha control) {
        FichaSeleccionada fichaSeleccionada = control.fichaSeleccionada();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        descargarCombobox = new JComboBox<>(fichaSeleccionada.fichasCargadas());
        descargarCombobox.setRenderer(new FichasCargadasRenderer());

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(movimientoLabel);
        panelStats.add(new JLabel("/"));
        panelStats.add(movimientoMaximoLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(barrasLabel);
        add(panelStats);

        JPanel panelBotonesMovimiento = new JPanel();
        panelBotonesMovimiento.add(botonArriba);
        panelBotonesMovimiento.add(botonAbajo);
        panelBotonesMovimiento.add(botonIzquierda);
        panelBotonesMovimiento.add(botonDerecha);
        add(panelBotonesMovimiento);

        JPanel panelBotonesDeAccion = new JPanel();
        panelBotonesDeAccion.add(botonAtaque);
        panelBotonesDeAccion.add(botonCargar);
        panelBotonesDeAccion.add(descargarCombobox);
        add(panelBotonesDeAccion);

        addActionListeners(control);

        botonAtaque.setMnemonic(KeyEvent.VK_A);
        botonCargar.setMnemonic(KeyEvent.VK_C);

        cambiarFicha(fichaSeleccionada.ficha());

        fichaSeleccionada.cambioDeFichaObservable().addObserver(new Observer<Ficha>() {
            @Override
            public void update(Observable<Ficha> o, Ficha ficha) {
                cambiarFicha(ficha);
            }
        });

        fichaSeleccionada.accionObservables().addObserver(new Observer<FichaSeleccionada>() {
            @Override
            public void update(Observable<FichaSeleccionada> o, FichaSeleccionada data) {
                actulizarFicha();
            }
        });
    }


    private void addActionListeners(final ControladorFicha control) {
        botonAbajo.addActionListener(control.getMovimientoListener(Direccion.ABAJO));
        botonArriba.addActionListener(control.getMovimientoListener(Direccion.ARRIBA));
        botonIzquierda.addActionListener(control.getMovimientoListener(Direccion.IZQUIERDA));
        botonDerecha.addActionListener(control.getMovimientoListener(Direccion.DERECHA));

        botonAtaque.addActionListener(control.getAtaqueListener());
        botonCargar.addActionListener(control.getCargarListener());

        KeyboardMap keyboardMap = new KeyboardMap(this);
        keyboardMap.put("UP", control.getMovimientoListener(Direccion.ARRIBA));
        keyboardMap.put("DOWN", control.getMovimientoListener(Direccion.ABAJO));
        keyboardMap.put("LEFT", control.getMovimientoListener(Direccion.IZQUIERDA));
        keyboardMap.put("RIGHT", control.getMovimientoListener(Direccion.DERECHA));
    }


    private void cambiarFicha(Ficha ficha) {
        this.ficha = ficha;
        actulizarFicha();
    }

    private void actulizarFicha() {
        nombreLabel.setText(ficha.nombre());
        movimientoLabel.setText(ficha.movimiento() + "");
        movimientoMaximoLabel.setText(ficha.movimientoMaximo() + "");
        barrasLabel.setText(ficha.barras().toShortString());
    }

    private static class FichasCargadasRenderer extends DefaultListCellRenderer {
        private static String string(Ficha ficha) {
            if (ficha == null)
                return "Descargar...";
            else
                return String.format("%s %d/%d %s",
                        ficha.nombre(),
                        ficha.movimiento(),
                        ficha.movimientoMaximo(),
                        ficha.barras().toShortString()
                );
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Ficha ficha = (Ficha) value;
            setText(string(ficha));
            return this;
        }
    }
}

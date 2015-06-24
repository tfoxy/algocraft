package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorFicha;
import gui.controlador.KeyboardMap;
import gui.modelo.FichaSeleccionada;
import gui.modelo.Observable;
import gui.modelo.Observer;
import magia.Magia;
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

    private final JPanel panelBotonesMovimiento;
    private final JPanel panelBotonesDeAccion;

    private final JLabel nombreLabel = new JLabel();
    private final JLabel movimientoLabel = new JLabel();
    private final JLabel barrasLabel = new JLabel();

    private final JButton botonArriba = new JButton("Arriba");
    private final JButton botonAbajo = new JButton("Abajo");
    private final JButton botonIzquierda = new JButton("Izquierda");
    private final JButton botonDerecha = new JButton("Derecha");
    private final JButton botonAtaque = new JButton("Ataque");
    private final JButton botonCargar = new JButton("Cargar");
    private final JComboBox<Ficha> descargarCombobox;
    private final JComboBox<Magia> magiasCombobox;


    public FichaView(ControladorFicha control) {
        FichaSeleccionada fichaSeleccionada = control.fichaSeleccionada();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        descargarCombobox = new JComboBox<>(fichaSeleccionada.fichasCargadas());
        descargarCombobox.setRenderer(new FichasCargadasRenderer());

        magiasCombobox = new JComboBox<>(fichaSeleccionada.magiasDisponibles());
        magiasCombobox.setRenderer(new MagiasDisponiblesRenderer());

        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" "));
        panelStats.add(movimientoLabel);
        add(panelStats);

        JPanel barrasPanel = new JPanel();
        barrasPanel.add(barrasLabel);
        add(barrasPanel);

        panelBotonesMovimiento = new JPanel();
        panelBotonesMovimiento.add(botonArriba);
        panelBotonesMovimiento.add(botonAbajo);
        panelBotonesMovimiento.add(botonIzquierda);
        panelBotonesMovimiento.add(botonDerecha);
        add(panelBotonesMovimiento);

        panelBotonesDeAccion = new JPanel();
        panelBotonesDeAccion.add(botonAtaque);
        panelBotonesDeAccion.add(botonCargar);
        panelBotonesDeAccion.add(descargarCombobox);
        panelBotonesDeAccion.add(magiasCombobox);
        add(panelBotonesDeAccion);

        addActionListeners(control);

        botonAtaque.setMnemonic(KeyEvent.VK_A);
        botonCargar.setMnemonic(KeyEvent.VK_C);

        fichaSeleccionada.cambioDeFichaObservable().addObserver(new CambioDeFicha());

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

        // Mostrar solamente los botones que sean necesarios para la ficha
        panelBotonesMovimiento.setVisible(ficha.movimientoMaximo() > 0);
        botonAtaque.setVisible(ficha.tieneAtaque());
        botonCargar.setVisible(ficha.capacidadEnTransporte() > 0);
        descargarCombobox.setVisible(ficha.capacidadEnTransporte() > 0);
        magiasCombobox.setVisible(ficha.magias().size() > 0);
    }

    private void actulizarFicha() {
        nombreLabel.setText(ficha.nombre());
        movimientoLabel.setText(getMovimientoString());
        barrasLabel.setText(ficha.barras().toShortString());
    }

    private String getMovimientoString() {
        if (ficha.movimientoMaximo() > 0) {
            return ficha.movimiento() + "/" + ficha.movimientoMaximo();
        } else {
            return "";
        }
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

    private static class MagiasDisponiblesRenderer extends DefaultListCellRenderer {
        private static String string(Magia magia) {
            if (magia == null)
                return "Emitir magia...";
            else
                return String.format("%s C:%d R:%d",
                        magia.nombre(),
                        magia.coste(),
                        magia.rango()
                );
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Magia magia = (Magia) value;
            setText(string(magia));
            return this;
        }
    }

    private class CambioDeFicha implements Observer<Ficha> {
        @Override
        public void update(Observable<Ficha> object, Ficha data) {
            cambiarFicha(data);
        }
    }
}

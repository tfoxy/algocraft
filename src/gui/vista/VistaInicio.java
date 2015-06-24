package gui.vista;


import gui.controlador.ControladorInicio;
import gui.modelo.ConfiguracionDeInicio;
import gui.modelo.PropiedadesDeJugador;
import juego.Raza;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInicio extends JPanel {

    public VistaInicio(ConfiguracionDeInicio model, ControladorInicio control, LoggerView loggerView) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel jugadoresPanel = new JPanel(new GridLayout(0, 1));

        for (PropiedadesDeJugador jugador: model.getJugadores()) {
            JPanel jugadorPanel = new JPanel();

            JTextField nombreField = new JTextField(jugador.getNombreModel(), null, 20);
            jugadorPanel.add(nombreField);

            JComboBox<Raza> razaCombobox = new JComboBox<>(jugador.getRazaModel());
            jugadorPanel.add(razaCombobox);

            JComboBox<Color> colorCombobox = new JComboBox<>(jugador.getColorModel());
            ColorChange colorChange = new ColorChange(colorCombobox);
            colorCombobox.setRenderer(new ColorRenderer());
            colorCombobox.addActionListener(colorChange);
            colorChange.update();
            jugadorPanel.add(colorCombobox);

            jugadoresPanel.add(jugadorPanel);
        }

        JPanel botones = new JPanel();
        Button botonJugar = new Button("Jugar");
        botones.add(botonJugar);

        add(jugadoresPanel);
        add(loggerView);
        add(botones);

        botonJugar.addActionListener(control.jugarListener());
    }

    private static class ColorRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Color color = (Color) value;
            String text = String.format("[r=%d,g=%d,b=%d]",
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue());
            setText(text);
            setBackground(color);
            return this;
        }
    }

    private static class ColorChange implements ActionListener {
        private final JComboBox<Color> combobox;

        public ColorChange(JComboBox<Color> combobox) {
            this.combobox = combobox;
        }

        private void update() {
            Color color = (Color) combobox.getSelectedItem();
            combobox.setBackground(color);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            update();
        }
    }
}

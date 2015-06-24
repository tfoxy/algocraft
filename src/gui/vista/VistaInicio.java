package gui.vista;


import gui.controlador.ControladorInicio;
import gui.modelo.ConfiguracionDeInicio;
import gui.modelo.PropiedadesDeJugador;
import juego.Raza;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;

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
}

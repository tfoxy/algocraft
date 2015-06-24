package gui.vista;

import gui.controlador.ControlFinDelJuego;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Jugador;

public class VistaGanador extends JFrame {


    private final JLabel nombreLabel = new JLabel();
    private Button botonSalir = new Button("Salir");

    public VistaGanador(Jugador jugador, ControlFinDelJuego control) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        JPanel panelStats = new JPanel();

        nombreLabel.setText(jugador.nombre());
        panelStats.add(new JLabel("El Jugador "));
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" A Ganado El juego"));
        panelStats.add(botonSalir);

        container.add(panelStats);

        setSize(500, 100);
        setVisible(true);

        botonSalir.addActionListener(control.salir(this));
    }
}

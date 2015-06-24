package gui.vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Jugador;

public class VistaGanador extends JPanel {

    public VistaGanador(Jugador jugador, ActionListener botonListener) {
        JButton botonAceptar = new JButton("Aceptar");
        add(new JLabel("El Jugador " + jugador.nombre() + " ha ganado el juego"));
        add(botonAceptar);

        botonAceptar.addActionListener(botonListener);
    }
}

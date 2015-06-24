package gui.vista;

import java.awt.Button;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Jugador;

public class VistaGanador extends JPanel {

    public VistaGanador(Jugador jugador, ActionListener botonListener) {
        Button botonSalir = new Button("Salir");
        add(new JLabel("El Jugador " + jugador.nombre() + " ha ganado el juego"));
        add(new Button("Aceptar"));

        botonSalir.addActionListener(botonListener);
    }
}

package gui.vista;

import gui.controlador.ControlFinDelJuego;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vista.VentanaRecursos.CloseListener;

import juego.Jugador;

public class VistaGanador extends JFrame implements Observer{
	

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

		addWindowListener(new CloseListener());
        
        container.add(panelStats);
        
		setSize(500,100);  
		setVisible(true); 
		
		botonSalir.addActionListener(control.salir(this));
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
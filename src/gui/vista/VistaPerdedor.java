package gui.vista;

import ficha.Ficha;
import gui.controlador.ControladorInicio;
import gui.controlador.ControladorJugador;
import gui.modelo.JugadorDeTurno;
import gui.modelo.RazasJugables;


import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Jugador;
import juego.Raza;

import vista.VentanaRecursos.CloseListener;

public class VistaPerdedor extends JFrame implements Observer{
	
    private Jugador jugador;

    private final JLabel nombreLabel = new JLabel();

    public VistaPerdedor(Jugador jugador) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        JPanel panelStats = new JPanel();
        
        nombreLabel.setText(jugador.nombre());
        panelStats.add(new JLabel("El Jugador "));
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" AsidoEliminadoDelJuego"));

        
        container.add(panelStats);
        
		setSize(500,100);  
		setVisible(true); 
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

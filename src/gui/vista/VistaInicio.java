package gui.vista;


import java.awt.Button;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Raza;

import vista.VentanaRecursos.CloseListener;

import ficha.Ficha;
import gui.controlador.ControladorInicio;
import gui.modelo.RazasJugables;

public class VistaInicio extends JFrame implements Observer{

	
	private final TextField textoNombre1 = new TextField("Nombre");
    private JComboBox<Raza> RazaCombobox1;
	private TextField textoNombre2 = new TextField("Nombre");
    private JComboBox<Raza> RazaCombobox2;
 
	private JLabel botones;
	private Button botonJugar = new Button("Jugar"); 
	
	public VistaInicio(ControladorInicio control) {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

		RazaCombobox1 =  new JComboBox<>(new RazasJugables());
		RazaCombobox2 =  new JComboBox<>(new RazasJugables());
        
        
		JPanel frameTempJugador1 = new JPanel();
		frameTempJugador1.add(textoNombre1);
		frameTempJugador1.add(RazaCombobox1);
		
		JPanel frameTempJugador2 = new JPanel();
		frameTempJugador2.add(textoNombre2);
		frameTempJugador2.add(RazaCombobox2);
		
		
		 container.add(frameTempJugador1);
		 container.add(frameTempJugador2);
		
		JPanel botones = new JPanel();
		botones.add(botonJugar);
		
		container.add(botones);
		setSize(500,100);  
		setVisible(true); 

		addWindowListener(new CloseListener());
		
		botonJugar.addActionListener(control.jugarListener(this));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public String getNombre1() {
		return textoNombre1.getText();
	}

	public Raza getRaza1() {
		return (Raza) RazaCombobox1.getSelectedItem();
	}

	public String getNombre2() {
		return textoNombre2.getText();
	}

	public Raza getRaza2() {
		return (Raza) RazaCombobox2.getSelectedItem();
	}
}

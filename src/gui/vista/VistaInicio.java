package gui.vista;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import juego.Raza;

import vista.VentanaRecursos.CloseListener;

import ficha.Ficha;
import gui.controlador.ControladorInicio;
import gui.modelo.RazasJugables;

public class VistaInicio extends JFrame implements Observer{
	
	private TextField[] textoNombres = { new TextField("Nombre"), new TextField("Nombre")};
    private JComboBox<Raza> RazaCombobox1; //hacer esto con array nos va a largar el codigo en ves de acortarlo.
    private JComboBox<Raza> RazaCombobox2;
 
	private JLabel botones;
	private Button botonJugar = new Button("Jugar"); 
	
	public VistaInicio(ControladorInicio control) {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

		RazaCombobox1 =  new JComboBox<>(new RazasJugables());
		RazaCombobox2 =  new JComboBox<>(new RazasJugables());
        
        
		JPanel frameTempJugador1 = new JPanel();
		frameTempJugador1.add(textoNombres[0]);
		frameTempJugador1.add(RazaCombobox1);
		
		JPanel frameTempJugador2 = new JPanel();
		frameTempJugador2.add(textoNombres[1]);
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
		
		URL url = null;
		try {
			url = new URL("http://www.mp3item.com/soundeffects/water02.wav");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioClip sonido = Applet.newAudioClip(url);
	//	sonido.play();
		sonido.loop();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub	
	}

	public Raza getRaza1() {
		return (Raza) RazaCombobox1.getSelectedItem();
	}

	public Raza getRaza2() {
		return (Raza) RazaCombobox2.getSelectedItem();
	}

	public String getNombre(int i) {
		return textoNombres[i].getText();
	}
}

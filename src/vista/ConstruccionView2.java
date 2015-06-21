package vista;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controladores.ControladorConstruccion;

import ficha.Ficha;
import ficha.protoss.edificio.Acceso;
import ficha.protoss.edificio.ArchivosTemplarios;
import ficha.protoss.edificio.Asimilador;
import ficha.protoss.edificio.NexoMineral;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.edificio.PuertoEstelarProtoss;

import juego.Jugador;

import modelo.Modelo;
import tablero.Direccion;
import vista.VentanaRecursos.CloseListener;

public class ConstruccionView2 extends JFrame{

	private Jugador jugador;
	private Frame frameTemp;
	
    private final JLabel nombreLabel = new JLabel();
	private final JLabel cristalLabel = new JLabel();
    private final JLabel gazLabel = new JLabel();
    private final JLabel poblacionLabel = new JLabel();
    private final JLabel poblacionMaximoLabel = new JLabel();
	
    
    private String[] fichas = new String[] {"Acceso","ArchivosTemplarios"}; 
    //esto deveria estaser en fichas de Raza. Pero mientras lo implementamos se qeuda aca.
    private final JButton botonConstruir = new JButton("construir");
    private JComboBox<String> fichasACrearList = new JComboBox<>(fichas);
    
	public ConstruccionView2(Jugador jugador) {
		

		frameTemp = new Frame("Recursos");
 
		frameTemp.setSize(500,500);  

		frameTemp.addWindowListener(new CloseListener());
		frameTemp.enable(true);


		
		//parte importante
		this.jugador = jugador;
        nombreLabel.setText(jugador.nombre());
        cristalLabel.setText(jugador.cantidadMineral() + "");
        gazLabel.setText(jugador.cantidadGas() + "");
        poblacionLabel.setText(jugador.poblcacionActual() + "");
        poblacionMaximoLabel.setText(jugador.poblacionPosible() + "");	
        
        JPanel panelStats = new JPanel();
        panelStats.add(nombreLabel);
        panelStats.add(new JLabel(" Cristal:"));
        panelStats.add(cristalLabel);
        panelStats.add(new JLabel(" Gaz:"));
        panelStats.add(gazLabel);      
        panelStats.add(new JLabel(" "));
        panelStats.add(poblacionLabel);
        panelStats.add(new JLabel("/"));
        panelStats.add(poblacionMaximoLabel);
        frameTemp.add(panelStats);
        
        JPanel panelConstruccion = new JPanel();
        panelConstruccion.add(fichasACrearList);
        panelConstruccion.add(botonConstruir);
        frameTemp.add(panelConstruccion);

	}
	
	
	
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
}
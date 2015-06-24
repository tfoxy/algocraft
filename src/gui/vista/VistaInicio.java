package gui.vista;


import gui.controlador.ControladorInicio;
import juego.Raza;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.TextField;

public class VistaInicio extends JFrame {

    private TextField[] textoNombres = {new TextField("Nombre"), new TextField("Nombre")};
    private JComboBox<Raza> razaCombobox1; //hacer esto con array nos va a largar el codigo en ves de acortarlo.
    private JComboBox<Raza> razaCombobox2;

    private JLabel botones;
    private Button botonJugar = new Button("Jugar");

    public VistaInicio(ControladorInicio control) {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        razaCombobox1 = new JComboBox<>(Raza.values());
        razaCombobox2 = new JComboBox<>(Raza.values());


        JPanel frameTempJugador1 = new JPanel();
        frameTempJugador1.add(textoNombres[0]);
        frameTempJugador1.add(razaCombobox1);

        JPanel frameTempJugador2 = new JPanel();
        frameTempJugador2.add(textoNombres[1]);
        frameTempJugador2.add(razaCombobox2);


        container.add(frameTempJugador1);
        container.add(frameTempJugador2);

        JPanel botones = new JPanel();
        botones.add(botonJugar);

        container.add(botones);
        setSize(500, 100);
        setVisible(true);

        botonJugar.addActionListener(control.jugarListener(this));

		/*URL url = null;
        try {
			url = new URL("file:///home/geco/git/algocraft/src/gui/vista/Musica.wav");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioClip sonido = Applet.newAudioClip(url);
		sonido.loop();
		Por si despues se quiere seguir intentando. pero por lo pronto se tendria que borrar.
		*/
    }

    public Raza getRaza1() {
        return (Raza) razaCombobox1.getSelectedItem();
    }

    public Raza getRaza2() {
        return (Raza) razaCombobox2.getSelectedItem();
    }

    public String getNombre(int i) {
        return textoNombres[i].getText();
    }
}

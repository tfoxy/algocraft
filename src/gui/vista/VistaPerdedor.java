package gui.vista;

import juego.Jugador;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.GridBagLayout;

public class VistaPerdedor extends JFrame {

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

        setSize(500, 100);
        setVisible(true);
    }

}

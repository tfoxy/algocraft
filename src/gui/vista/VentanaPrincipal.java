package gui.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(JPanel grilla, JPanel fichaView, JPanel jugadorView) {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        container.add(grilla, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(fichaView, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        container.add(jugadorView, gbc);

        grilla.requestFocus();

        setPropiedadesDeVentana();
    }

    private void setPropiedadesDeVentana() {
        setTitle("AlgoCraft");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Mostrar Tooltips inmediatamente (sin delay)
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }

}

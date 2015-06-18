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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        container.add(grilla, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        container.add(fichaView, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
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

package gui.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(JPanel grilla, JPanel fichaView, JPanel jugadorView, JPanel loggerView) {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        container.add(loggerView, gbc);

        gbc.gridy++;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        container.add(grilla, gbc);

        gbc.gridy++;

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(fichaView, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
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
    }

    public static void iniciarPropiedadesGlobales() {
        // Mostrar Tooltips inmediatamente (sin delay)
        ToolTipManager.sharedInstance().setInitialDelay(0);

        // enable anti-aliased text:
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
    }

    public static class CloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}

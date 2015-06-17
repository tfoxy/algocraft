package gui.vista;

import javax.swing.JFrame;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;

public class VentanaPrincipal {

    private JFrame frame;

    public VentanaPrincipal(JFrame frame) {
        this.frame = frame;

        frame.setTitle("AlgoCraft");
        frame.setSize(700, 500);
        frame.setLocation(8, 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Mostrar Tooltips inmediatamente (sin delay)
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

}

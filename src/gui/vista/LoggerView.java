package gui.vista;

import gui.modelo.JuegoLogger;
import gui.modelo.Observable;
import gui.modelo.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerView extends JPanel {

    private static final int TIME = 2000;


    private final JLabel label = new JLabel();
    private final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("");
            label.repaint();
        }
    };
    private final Timer timer = new Timer(TIME, listener);

    public LoggerView(JuegoLogger logger) {

        logger.addObserver(new Observer<String>() {
            @Override
            public void update(Observable<String> object, String data) {
                actualizar(data);
            }
        });
    }

    private void actualizar(String msg) {
        label.setText(msg);
        timer.restart();
    }
}

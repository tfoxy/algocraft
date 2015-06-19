package gui.vista;

import gui.modelo.JuegoLogger;
import gui.modelo.Observable;
import gui.modelo.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggerView extends JPanel {

    private static final int TIME = 4000;


    private final JLabel label = new JLabel(" ");
    private final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText(" ");
            label.repaint();
        }
    };
    private final Timer timer = new Timer(TIME, listener);

    public LoggerView(JuegoLogger logger) {
        super(new BorderLayout());

        setearPropiedadesGraficas();

        timer.setRepeats(false);

        add(label);

        logger.addObserver(new Observer<String>() {
            @Override
            public void update(Observable<String> object, String data) {
                actualizar(data);
            }
        });
    }

    private void setearPropiedadesGraficas() {
        setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        Font newFont = label.getFont().deriveFont(10f);
        label.setFont(newFont);
    }

    public void actualizar(String msg) {
        label.setText(msg);
        timer.restart();
    }
}

package gui.controlador;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class AnyEventListener implements ActionListener, KeyListener {
    public abstract void eventOcurred(AWTEvent e);

    @Override
    public void actionPerformed(ActionEvent e) {
        eventOcurred(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        eventOcurred(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        eventOcurred(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        eventOcurred(e);
    }
}

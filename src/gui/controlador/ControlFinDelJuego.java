package gui.controlador;

import gui.vista.VistaGanador;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ControlFinDelJuego {

    private class SalirListener extends AnyEventListener {

    	private VistaGanador ventana;
        private SalirListener(VistaGanador ventana) {
            this.ventana = ventana;
        }

        @Override
        public void eventOcurred(AWTEvent e) {
        	ventana.dispatchEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSING));
        }
    }

    public AnyEventListener salir(VistaGanador ventana) {
        return new SalirListener(ventana);
    }

}

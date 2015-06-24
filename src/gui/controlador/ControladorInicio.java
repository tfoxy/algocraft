package gui.controlador;

import gui.modelo.ConfiguracionDeInicio;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;

public class ControladorInicio {
    private final ConfiguracionDeInicio config;

    public ControladorInicio(ConfiguracionDeInicio config) {
        this.config = config;
    }

    private final class JugadorListener extends AnyEventListener {
        @Override
        public void eventOcurred(AWTEvent e) {
            config.iniciarJuego();
        }
    }

	public ActionListener jugarListener() {
		return new JugadorListener();
	}


}

package controladores;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import error.JuegoException;
import error.UnicamenteObjetivoPropioException;
import gui.controlador.AnyEventListener;
import gui.controlador.KeyboardEvents;
import gui.modelo.AccionDeFicha;
import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.FichaSeleccionada;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.ObservableActions;
import gui.modelo.ObservableEnumActions;
import gui.modelo.Observer;
import magia.Magia;
import tablero.Direccion;

import ficha.Ficha;

public class ControladorFicha {

    private final FichaSeleccionada fichaSeleccionada;

    public ControladorFicha(FichaSeleccionada fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
    }


    public FichaSeleccionada fichaSeleccionada() {
        return fichaSeleccionada;
    }


    private final class MovimientoListener extends AnyEventListener {
        private final Direccion direccion;

        private MovimientoListener(Direccion direccion) {
            this.direccion = direccion;
        }

        @Override
        public void eventOcurred(AWTEvent e) {
            fichaSeleccionada.moverHacia(direccion);
        }
    }

    public AnyEventListener getMovimientoListener(Direccion direccion) {
        return new MovimientoListener(direccion);
    }

    public AnyEventListener getAtaqueListener() {
        return new AnyEventListener() {
            @Override
            public void eventOcurred(AWTEvent e) {
                fichaSeleccionada.modoAtaque();
            }
        };
    }
}

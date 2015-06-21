package controladores;

import gui.controlador.AnyEventListener;
import gui.modelo.FichaSeleccionada;
import tablero.Direccion;

import java.awt.AWTEvent;

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

    public AnyEventListener getCargarListener() {
        return new AnyEventListener() {
            @Override
            public void eventOcurred(AWTEvent e) {
                fichaSeleccionada.cargar();
            }
        };
    }

}

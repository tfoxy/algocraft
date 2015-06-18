package gui.controlador;

import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import juego.Juego;
import juego.Jugador;
import tablero.Direccion;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

public class ControladorJugador {

    private Juego juego;
    private ElementObservable<Jugador> cambioDeJugadorObservable;

    public ControladorJugador(Juego juego) {
        this.juego = juego;
        cambioDeJugadorObservable = new ElementObservable<>(juego.jugadorActual());
    }

    public void terminarTurno() {
        this.juego.pasarJugador();
        cambioDeJugadorObservable.setAndNotify(this.juego.jugadorActual());
    }

    public Jugador jugador() {
        return cambioDeJugadorObservable.get();
    }

    public void observarCambioDeJugador(ElementObserver<Jugador> o) {
        cambioDeJugadorObservable.addObserver(o);
    }

    private class TerminarTurnoListener extends AnyEventListener {

        private TerminarTurnoListener() {
            // noop
        }

        @Override
        public void eventOcurred(AWTEvent e) {
            terminarTurno();
        }
    }

    public AnyEventListener getTerminarTurnoListener() {
        return new TerminarTurnoListener();
    }

    public void listenKeyboard(KeyboardEvents keyboard) {
        final int ev = KeyEvent.KEY_PRESSED;

        keyboard.addListener(ev, KeyEvent.VK_SPACE, new TerminarTurnoListener());
    }

}

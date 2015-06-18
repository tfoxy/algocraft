package gui.controlador;

import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import juego.Juego;
import juego.Jugador;

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

}

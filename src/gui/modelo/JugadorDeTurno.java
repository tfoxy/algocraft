package gui.modelo;

import ficha.Ficha;
import juego.Juego;
import juego.Jugador;
import tablero.Coordenada;

import java.util.NoSuchElementException;

public class JugadorDeTurno {

    private final Juego juego;
    private final FichaObjetivo fichaObjetivo;
    private final FichaParaConstruir fichaParaConstruir;
    private final Observable<JugadorDeTurno> comenzarTurnoObservable;
    private final Observable<JugadorDeTurno> terminarTurnoObservable;

    public JugadorDeTurno(Juego juego, FichaObjetivo fichaObjetivo) {
        this.juego = juego;
        this.fichaObjetivo = fichaObjetivo;
        this.fichaParaConstruir = new FichaParaConstruir(fichaObjetivo, this);
        this.comenzarTurnoObservable = new Observable<>();
        this.terminarTurnoObservable = new Observable<>();

        this.comenzarTurnoObservable.addObserver(new SeleccionarPrimeraFicha());
    }


    private class SeleccionarPrimeraFicha implements Observer<JugadorDeTurno> {
        @Override
        public void update(Observable<JugadorDeTurno> object, JugadorDeTurno data) {
            fichaObjetivo.cambiarAccion(AccionEnGrilla.SELECCION);
            fichaObjetivo.cambiarFicha(elegirNuevaFicha());
        }
    }


    private Ficha elegirNuevaFicha() {
        try {
            return jugador().fichas().iterator().next();
        } catch (NoSuchElementException exception) {
            return juego.tablero().getFichaTerrestre(new Coordenada(1, 1));
        }
    }


    public Jugador jugador() {
        return juego.jugadorActual();
    }

    public Juego juego() {
        return juego;
    }

    public FichaParaConstruir fichaParaConstruir() {
        return fichaParaConstruir;
    }

    public void terminarTurno() {
        this.terminarTurnoObservable.notifyObservers(this);

        this.juego.pasarJugador();

        this.comenzarTurnoObservable.notifyObservers(this);
    }

    public Observable<JugadorDeTurno> comenzarTurnoObservable() {
        return comenzarTurnoObservable;
    }

    public Observable<JugadorDeTurno> terminarTurnoObservable() {
        return terminarTurnoObservable;
    }

    public void comenzarTurno() {
        this.comenzarTurnoObservable.notifyObservers(this);
    }
}

package juego;

import com.google.common.collect.Iterators;
import gui.modelo.TableroObservable;
import tablero.ITablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Juego extends Observable {

    private final List<Jugador> jugadores;
    private final ITablero tablero;
    private final Iterator<Jugador> jugadorActualIterator;
    private Jugador jugadorActual;

    private Juego(Builder builder) {
        jugadores = builder.jugadores;
        tablero = builder.tablero;

        jugadorActualIterator = Iterators.cycle(jugadores);

        pasarJugador();
    }

    public Gaia gaia() {
        return tablero.gaia();
    }

    public ITablero tablero() {
        return tablero;
    }

    public List<Jugador> jugadores() {
        return jugadores;
    }

    public Jugador jugadorActual() {
        return jugadorActual;
    }

    public void pasarTurno() {
        if (!estaTerminado()) {
            jugadorActual().pasarTurno();

            quitarPerdedoresYPasarJugador();
        }
    }

    public boolean estaTerminado() {
        return jugadores.size() <= 1;
    }

    private void quitarPerdedoresYPasarJugador() {
        int cantidadDeJugadores = jugadores.size();
        Jugador jugadorAux = jugadorActual;

        for (int i = 0; i < cantidadDeJugadores; i++) {
            if (jugadorActual.perdi())
                jugadorActualIterator.remove();
            pasarJugador();
        }

        if (jugadorActual.equals(jugadorAux))
            pasarJugador();
    }

    private void pasarJugador() {
        if (jugadorActualIterator.hasNext()) {
            jugadorActual = jugadorActualIterator.next();
        } else {
            // En caso de que no haya más jugadores, se considera que gaia ganó la partida
            jugadorActual = gaia();
        }
    }


    public static class Builder {
        private final List<Jugador> jugadores = new ArrayList<>();
        private ITablero tablero = null;


        public Builder agregarJugador(Jugador jugador) {
            jugadores.add(jugador);
            return this;
        }


        public Builder tablero(ITablero tablero) {
            if (tablero == null) {
                throw new NullPointerException();
            }

            this.tablero = tablero;
            return this;
        }


        public TableroObservable hacerTableroObservable() {
            TableroObservable tableroObservable = new TableroObservable(tablero);
            this.tablero = tableroObservable;
            return tableroObservable;
        }


        public Juego build() {
            if (tablero == null) {
                throw new IllegalStateException();
            }

            return new Juego(this);
        }


        public ITablero tablero() {
            return tablero;
        }
    }

}

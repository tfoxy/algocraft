package juego;

import gui.modelo.TableroObservable;
import tablero.ITablero;
import tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Juego extends Observable {

    private final Gaia gaia;
    private final List<Jugador> jugadores;
    private final ITablero tablero;
    private int jugadorActualIndex = 0;

    private Juego(Builder builder) {
        gaia = builder.gaia;
        jugadores = builder.jugadores;
        tablero = builder.tablero;
    }

    public Gaia gaia() {
        return gaia;
    }

    public ITablero tablero() {
        return tablero;
    }

    public List<Jugador> jugadores() {
        return jugadores;
    }

    public Jugador jugadorActual() {
        return jugadores.get(jugadorActualIndex);
    }

    public void pasarJugador() {
        jugadorActual().pasarTurno();

        jugadorActualIndex++;
        if (jugadorActualIndex >= jugadores.size())
            jugadorActualIndex = 0;
    }


    public static class Builder {
        private static final int POBLACION_MAXIMA = 200;

        private final Gaia gaia = new Gaia();
        private final List<Jugador> jugadores = new ArrayList<>();
        private ITablero tablero = null;
        private int poblacionMaxima = POBLACION_MAXIMA;


        public Gaia gaia() {
            return gaia;
        }


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


		public Tablero tablero() { //cosas testaduras del compilador
			return (Tablero) tablero;
		}
    }

}

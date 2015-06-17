package juego;

import gui.modelo.TableroObservable;
import tablero.ITablero;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private final Gaia gaia;
    private final List<Jugador> jugadores;
    private final ITablero tablero;

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
    }

}

package juego;

import gui.modelo.TableroObservable;
import tablero.ITablero;
import tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private final Gaia gaia;
    private final List<Jugador> jugadores;
    private ITablero tablero;

    private Juego(Builder builder) {
        gaia = new Gaia();
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

    public TableroObservable hacerTableroObservable() {
        TableroObservable tableroObservable = new TableroObservable(tablero);
        this.tablero = tableroObservable;
        return tableroObservable;
    }


    public static class Builder {
        private static final int POBLACION_MAXIMA = 200;

        private List<Jugador> jugadores = new ArrayList<>();
        private ITablero tablero = null;
        private int poblacionMaxima = POBLACION_MAXIMA;


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


        public Juego build() {
            if (tablero == null) {
                throw new IllegalStateException();
            }


            return new Juego(this);
        }
    }

}

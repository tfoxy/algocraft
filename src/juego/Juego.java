package juego;

import tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private final Gaia gaia;
    private final List<Jugador> jugadores;
    private final Tablero tablero;

    private Juego(Builder builder) {
        gaia = new Gaia();
        jugadores = builder.jugadores;
        tablero = builder.tablero;
    }

    public Gaia gaia() {
        return gaia;
    }

    public Tablero tablero() {
        return tablero;
    }

    public List<Jugador> jugadores() {
        return jugadores;
    }


    public static class Builder {
        private static final int POBLACION_MAXIMA = 200;

        private List<Jugador> jugadores = new ArrayList<>();
        private Tablero tablero = null;
        private int poblacionMaxima = POBLACION_MAXIMA;


        public Builder agregarJugador(Jugador jugador) {
            jugadores.add(jugador);
            return this;
        }


        public Builder tablero(Tablero tablero) {
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

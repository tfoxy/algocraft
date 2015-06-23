package error;

import tablero.Coordenada;

public class PosicionFueraDeLimiteException extends JuegoException {
    public PosicionFueraDeLimiteException(Coordenada lugar) {
        super("Posición " + lugar.toString() + " fuera de mapa");
    }
}

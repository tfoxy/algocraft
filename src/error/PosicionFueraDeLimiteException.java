package error;

public class PosicionFueraDeLimiteException extends JuegoException {
    public PosicionFueraDeLimiteException() {
        super("Posición fuera de mapa");
    }

    public PosicionFueraDeLimiteException(String message) {
        super(message);
    }
}

package error;

public class JuegoException extends RuntimeException {

    public JuegoException() {
        super("No es posible realizar acción en el juego");
    }


    public JuegoException(String msg) {
        super(msg);
    }


    public JuegoException(String message, Throwable cause) {
        super(message, cause);
    }


    public JuegoException(Throwable cause) {
        super(cause);
    }

}

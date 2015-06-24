package error;

public class JugadoresConMismoColorException extends JuegoException {
    public JugadoresConMismoColorException() {
        super("Los jugadores no pueden tener el mismo color");
    }

    public JugadoresConMismoColorException(String msg) {
        super(msg);
    }
}

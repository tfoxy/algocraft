package error;

public class JugadoresConMismoNombreException extends JuegoException {
    public JugadoresConMismoNombreException() {
        super("Los jugadores no pueden tener el mismo nombre");
    }

    public JugadoresConMismoNombreException(String msg) {
        super(msg);
    }
}

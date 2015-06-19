package error;

public class FueraDeRangoException extends JuegoException {
    public FueraDeRangoException() {
        super("Objetivo fuera de rango");
    }

    public FueraDeRangoException(String msg) {
        super(msg);
    }
}

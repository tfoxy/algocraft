package error;

public class DentroDeTransporteException extends JuegoException {
    public DentroDeTransporteException() {
        super("Unidad se encuentra dentro de transporte");
    }

    public DentroDeTransporteException(String msg) {
        super(msg);
    }
}

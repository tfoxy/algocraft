package error;

public class MovimientoInsuficienteException extends JuegoException {

    public MovimientoInsuficienteException() {
        super("No queda movimiento suficiente para realizar acción");
    }

    public MovimientoInsuficienteException(String msg) {
        super(msg);
    }

}

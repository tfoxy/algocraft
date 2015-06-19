package error;

public class CapacidadInsuficienteException extends TransporteException {
    public CapacidadInsuficienteException() {
        super("Capacidad Insuficiente en el transporte");
    }

    public CapacidadInsuficienteException(String msg) {
        super(msg);
    }
}

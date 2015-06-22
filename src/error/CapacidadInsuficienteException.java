package error;

public class CapacidadInsuficienteException extends TransporteException {
    public CapacidadInsuficienteException() {
        super("Capacidad insuficiente en la unidad seleccionada");
    }

    public CapacidadInsuficienteException(String msg) {
        super(msg);
    }
}

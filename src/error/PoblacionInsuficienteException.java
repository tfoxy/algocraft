package error;

public class PoblacionInsuficienteException extends RecursosInsuficientesException {
    public PoblacionInsuficienteException() {
        super("No hay población disponible suficiente");
    }

    public PoblacionInsuficienteException(String msg) {
        super(msg);
    }
}

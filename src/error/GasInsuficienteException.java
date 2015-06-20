package error;

public class GasInsuficienteException extends RecursosInsuficientesException {
    public GasInsuficienteException() {
        super("Gas insuficiente");
    }

    public GasInsuficienteException(String msg) {
        super(msg);
    }
}

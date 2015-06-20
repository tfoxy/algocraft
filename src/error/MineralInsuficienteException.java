package error;

public class MineralInsuficienteException extends RecursosInsuficientesException {
    public MineralInsuficienteException() {
        super("Mineral insuficiente");
    }

    public MineralInsuficienteException(String msg) {
        super(msg);
    }
}

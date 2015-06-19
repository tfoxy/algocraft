package error;

public class EnergiaInsuficienteException extends JuegoException {
    public EnergiaInsuficienteException() {
        super("Energía insuficiente");
    }

    public EnergiaInsuficienteException(String msg) {
        super(msg);
    }
}

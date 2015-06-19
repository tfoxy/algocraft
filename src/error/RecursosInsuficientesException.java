package error;

public class RecursosInsuficientesException extends NoSePuedeCrearFicha {
    public RecursosInsuficientesException() {
        super("No hay recursos suficientes");
    }

    public RecursosInsuficientesException(String msg) {
        super(msg);
    }
}

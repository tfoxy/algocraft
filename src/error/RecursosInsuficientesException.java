package error;

public class RecursosInsuficientesException extends NoSePuedeCrearFicha {
    public RecursosInsuficientesException() {
    }

    public RecursosInsuficientesException(String msg) {
        super(msg);
    }
}

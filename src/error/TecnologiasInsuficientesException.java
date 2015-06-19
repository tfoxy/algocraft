package error;

public class TecnologiasInsuficientesException extends NoSePuedeCrearFicha {

    public TecnologiasInsuficientesException() {
        super("Falta desarrollar tecnologías");
    }

    public TecnologiasInsuficientesException(String msg) {
        super(msg);
    }
}

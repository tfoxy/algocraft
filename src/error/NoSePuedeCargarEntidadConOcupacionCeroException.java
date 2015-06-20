package error;

public class NoSePuedeCargarEntidadConOcupacionCeroException extends TransporteException {
    public NoSePuedeCargarEntidadConOcupacionCeroException() {
        super("No se puede cargar este tipo de entidad");
    }

    public NoSePuedeCargarEntidadConOcupacionCeroException(String msg) {
        super(msg);
    }
}

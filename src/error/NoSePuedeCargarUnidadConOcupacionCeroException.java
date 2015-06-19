package error;

public class NoSePuedeCargarUnidadConOcupacionCeroException extends TransporteException {
    public NoSePuedeCargarUnidadConOcupacionCeroException() {
        super("No se puede cargar unidad con ocupación cero");
    }

    public NoSePuedeCargarUnidadConOcupacionCeroException(String msg) {
        super(msg);
    }
}

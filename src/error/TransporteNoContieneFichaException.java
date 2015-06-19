package error;

public class TransporteNoContieneFichaException extends TransporteException {
    public TransporteNoContieneFichaException() {
        super("Transporte no contiene la unidad a descargar");
    }

    public TransporteNoContieneFichaException(String msg) {
        super(msg);
    }
}

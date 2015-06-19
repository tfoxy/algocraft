package error;

public class UnicamenteObjetivoUnidadException extends JuegoException {
    public UnicamenteObjetivoUnidadException() {
        super("Se puede aplicar solamente a unidades");
    }

    public UnicamenteObjetivoUnidadException(String msg) {
        super(msg);
    }
}

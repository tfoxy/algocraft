package error;

public class UnicamenteObjetivoNoEnConstruccionException extends JuegoException {
    public UnicamenteObjetivoNoEnConstruccionException() {
        super("No se puede elegir entidad en construcción");
    }

    public UnicamenteObjetivoNoEnConstruccionException(String msg) {
        super(msg);
    }
}

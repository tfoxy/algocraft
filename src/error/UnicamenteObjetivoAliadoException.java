package error;

public class UnicamenteObjetivoAliadoException extends JuegoException {
    public UnicamenteObjetivoAliadoException() {
        super("Solamente se puede realizar en unidades aliadas");
    }

    public UnicamenteObjetivoAliadoException(String msg) {
        super(msg);
    }
}

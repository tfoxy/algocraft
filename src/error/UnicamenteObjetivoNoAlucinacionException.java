package error;

public class UnicamenteObjetivoNoAlucinacionException extends JuegoException {
    public UnicamenteObjetivoNoAlucinacionException() {
        super("No se puede aplicar a alucinaciones");
    }

    public UnicamenteObjetivoNoAlucinacionException(String msg) {
        super(msg);
    }
}

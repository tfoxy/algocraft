package error;

public class UnicamenteObjetivoNoNaturalException extends JuegoException {
    public UnicamenteObjetivoNoNaturalException() {
        super("Se debe elegir en entidades de algun jugador");
    }

    public UnicamenteObjetivoNoNaturalException(String msg) {
        super(msg);
    }
}

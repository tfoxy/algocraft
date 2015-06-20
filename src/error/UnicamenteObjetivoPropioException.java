package error;

public class UnicamenteObjetivoPropioException extends JuegoException {
    public UnicamenteObjetivoPropioException() {
        super("Solamente se puede realizar en unidades propias");
    }

    public UnicamenteObjetivoPropioException(String msg) {
        super(msg);
    }
}

package error;

public class EstadoYaExisteEnFichaException extends JuegoException {
    public EstadoYaExisteEnFichaException() {
        super("Estado ya existe en la entidad");
    }

    public EstadoYaExisteEnFichaException(String msg) {
        super(msg);
    }
}

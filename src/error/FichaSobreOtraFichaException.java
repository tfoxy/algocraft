package error;

public class FichaSobreOtraFichaException extends NoSePuedeCrearFicha {
    public FichaSobreOtraFichaException() {
        super("Lugar ocupado por otra entidad");
    }

    public FichaSobreOtraFichaException(String msg) {
        super(msg);
    }
}

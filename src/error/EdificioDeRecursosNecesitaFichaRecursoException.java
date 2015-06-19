package error;

public class EdificioDeRecursosNecesitaFichaRecursoException extends NoSePuedeCrearFicha {
    public EdificioDeRecursosNecesitaFichaRecursoException() {
        super("Edificio de recursos puede construirse solamente sobre recursos");
    }

    public EdificioDeRecursosNecesitaFichaRecursoException(String msg) {
        super(msg);
    }
}

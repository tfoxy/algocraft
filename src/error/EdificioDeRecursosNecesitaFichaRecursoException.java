package error;

public class EdificioDeRecursosNecesitaFichaRecursoException extends NoSePuedeCrearFicha {
    public EdificioDeRecursosNecesitaFichaRecursoException() {
        super("Solamente puede construirse sobre el recurso apropiado");
    }

    public EdificioDeRecursosNecesitaFichaRecursoException(String msg) {
        super(msg);
    }
}

package error;

import juego.Tecnologia;

public class TecnologiaInsuficienteException extends TecnologiasInsuficientesException {
    private final Tecnologia tecnologia;

    public TecnologiaInsuficienteException(Tecnologia tecnologia) {
        super("Falta desarrollar " + tecnologia.nombre());
        this.tecnologia = tecnologia;
    }

    public Tecnologia tecnologia() {
        return tecnologia;
    }
}

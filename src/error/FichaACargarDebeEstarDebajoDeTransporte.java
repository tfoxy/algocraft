package error;

public class FichaACargarDebeEstarDebajoDeTransporte extends TransporteException {
    public FichaACargarDebeEstarDebajoDeTransporte() {
        super("Unidad a cargar debe estar debajo de transporte");
    }

    public FichaACargarDebeEstarDebajoDeTransporte(String msg) {
        super(msg);
    }
}

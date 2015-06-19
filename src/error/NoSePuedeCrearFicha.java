package error;

public class NoSePuedeCrearFicha extends JuegoException {

    public NoSePuedeCrearFicha() {
        super("No se puede crear entidad");
    }

    public NoSePuedeCrearFicha(String msg) {
        super(msg);
    }

}

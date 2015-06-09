package error;

public class NoSePuedeCrearFicha extends JuegoException {

    public NoSePuedeCrearFicha() {

    }

    public NoSePuedeCrearFicha(String msg) {
        super(msg);
    }

}

package estrategia.ficha;

import error.NoSePuedeCrearFicha;
import ficha.Ficha;


public abstract class EstrategiaFicha {

    public boolean mePuedeCrear() throws NoSePuedeCrearFicha {
        return false;
    }

    public void creame(Ficha nueva) {
    }

    public EstrategiaFicha pasarTurno() {
        return this;
    }

    public void morir(Ficha ficha) {

    }


}

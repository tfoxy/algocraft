package estrategia.ficha;

import error.NoSePuedeCrearFicha;
import ficha.Ficha;


public abstract class EstrategiaFicha {

    protected Ficha ficha;

    public EstrategiaFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void creame(Ficha nueva) {

    }

    public EstrategiaFicha pasarTurno() {
        return this;
    }

    public void morir(Ficha ficha) {

    }


}

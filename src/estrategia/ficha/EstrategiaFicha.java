package estrategia.ficha;

import error.NoSePuedeCrearFicha;
import ficha.Ficha;


public abstract class EstrategiaFicha {

    protected Ficha ficha;

    public EstrategiaFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void creame() {
        // noop
    }

    public void pasarTurno() {
        // noop
    }

    public void morir() {
        // noop
    }


}

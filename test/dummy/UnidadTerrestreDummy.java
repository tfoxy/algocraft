package dummy;

import ficha.UnidadTerrestre;
import juego.Recursos;

public class UnidadTerrestreDummy extends UnidadTerrestre {

    public UnidadTerrestreDummy() {
        this.coste = new Recursos(0, 0, 1);
    }

    @Override
    public void pasarTurno() {
        // noop
    }
}

package jugador;

import tecnologia.Tecnologia;

public class Gaia extends TablaJugador {

    public Gaia() {
        super("Gaia", Tecnologia.GAIA);
    }

    @Override
    public boolean tengoSuficientesRecursos(Recursos coste) {
        return true;
    }

}

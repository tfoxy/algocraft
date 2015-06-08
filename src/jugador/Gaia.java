package jugador;

public class Gaia extends TablaJugador {

    public Gaia() {
        super("Gaia", Tecnologia.GAIA);
    }

    @Override
    public boolean tengoSuficientesRecursos(Recursos coste) {
        return true;
    }

}

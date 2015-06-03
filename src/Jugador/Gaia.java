package Jugador;

public class Gaia extends TablaJugador {

    public Gaia() {
        super("Gaia", "Gaia");
    }

    @Override
    public boolean TengoSuficientesRecursos(Recursos coste) {
        return true;
    }

}

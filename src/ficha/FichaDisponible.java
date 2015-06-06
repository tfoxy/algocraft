package ficha;

import jugador.Recursos;
import jugador.TablaJugador;

public enum FichaDisponible {
    MARINE;

    private FichaDeJugador.Builder builder;

    public FichaDeJugador crear(TablaJugador propietario) {
        return builder.crear(propietario);
    }

    static {
        MARINE.builder = new FichaDeJugador.Builder()
                .vida(40)
                .coste(new Recursos(50, 0, 1))
                .ataque(6)
                .rangoDeAtaque(4)
                .transporteMaximo(1)
                .vision(7)
                .tiempoDeConstruccion(3)
                .movimiento(3)
                .nombre("Marine");
    }




}

package error;

import tablero.Coordenada;

public class CasillaFueraDeVisionException extends JuegoException {
    private final Coordenada coordenada;

    public CasillaFueraDeVisionException(Coordenada coordenada) {
        super("Casilla " + coordenada + " no se encuentra visible");
        this.coordenada = coordenada;
    }

    public Coordenada coordenada() {
        return coordenada;
    }
}

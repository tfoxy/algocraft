package magia;

import tablero.Coordenada3d;
import ficha.Ficha;
import tablero.ITablero;

/**
 * Inflige 100 unidades de daño a todas las unidades
 * que se encuentren debajo de la tormenta durante 2 turnos.
 * Cuesta 75 energía
 */
public class TormentaPsionicaMagia extends Magia {

    public TormentaPsionicaMagia() {
        super(75, 5);
    }

    // TODO test no se pueden crear dos tormentas en el mismo lugar

    @Override
    protected void aplicar(Ficha ficha, Coordenada3d objetivo) {
        final TormentaPsionicaFicha poder = new TormentaPsionicaFicha();
        final ITablero mapa = ficha.tablero();
        poder.setBasico(mapa.gaia(), mapa, objetivo);
        poder.ponerEnJuego();
    }
}

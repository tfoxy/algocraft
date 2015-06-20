package magia;

import tablero.Coordenada3d;
import ficha.Ficha;

/**
 * Inflige 100 unidades de daño a todas las unidades
 * que se encuentren debajo de la tormenta durante 2 turnos.
 * Cuesta 75 energía
 */
public class TormentaPsionicaMagia extends Magia {

    public TormentaPsionicaMagia() {
        super(75, 5);
    }

    @Override
    protected void aplicar(Ficha ficha, Coordenada3d objetivo) {
        final TormentaPsionicaFicha poder = new TormentaPsionicaFicha();
        poder.setBasico(ficha.propietario(), ficha.tablero(), objetivo);
        ficha.propietario().newFicha(poder);
    }
}

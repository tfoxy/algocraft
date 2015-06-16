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
    public void realizar(Ficha ficha, Coordenada3d objetivo) {
        super.realizar(ficha, objetivo);
        TormentaPsionica poder = new TormentaPsionica();
        poder.setBasico(ficha.propietario(), ficha.tablero(), objetivo.proyeccion());
        ficha.propietario().newFicha(poder);
    }
}

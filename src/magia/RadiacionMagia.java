package magia;

import error.UnicamenteObjetivoNoNaturalException;
import ficha.Ficha;
import tablero.Coordenada3d;
import tablero.Tablero;

/**
 * Afecta a una unidad en particular y la consume lentamente hasta matarla.
 * Cualquier otra unidad que esté exactamente al lado,
 * también pierde vida mientras esté a distancia 1 del irradiado.
 * Cuesta 75 de energía.
 */
public class RadiacionMagia extends Magia {

    public RadiacionMagia() {
        super(75, 5);
    }

    @Override
    protected void verificarObjetivo(Ficha ficha, Coordenada3d objetivo) {
        Tablero mapa = ficha.tablero();
        Ficha fichaObjetivo = mapa.getFicha(objetivo);

        if (fichaObjetivo.esNatural()) {
            throw new UnicamenteObjetivoNoNaturalException();
        }
    }

}

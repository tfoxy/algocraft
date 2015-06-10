package magia;

import error.UnicamenteObjetivoAliadoException;
import ficha.Ficha;
import tablero.Coordenada3d;
import tablero.Tablero;

/**
 * Es capaz de crear 2 copias de una propia unidad que son
 * simples “copias falsas” Son unidades que simulan ser unidades propias
 * pero al atacar no causan daño. Se crean para que reciban daño.
 * Los enemigos no pueden distinguir una unidad alucinada de una real.
 * Cuesta 100 de energía.
 * Las unidades alucinadas ​NO​ tienen vida, solo escudo.
 */
public class AlucinacionMagia extends Magia {

    public AlucinacionMagia() {
        super(100, 4);
    }

    @Override
    protected void verificarObjetivo(Ficha ficha, Coordenada3d objetivo) {
        Tablero mapa = ficha.tablero();
        Ficha fichaObjetivo = mapa.getFicha(objetivo);

        if (!fichaObjetivo.propietario().equals(ficha.propietario())) {
            // TODO test para probar que solamente se puede aplicar a ficha del mismo jugador
            throw new UnicamenteObjetivoAliadoException();
        }
    }

}

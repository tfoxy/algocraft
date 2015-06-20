package magia;

import java.util.HashSet;
import java.util.Set;

import error.FichaSobreOtraFichaException;
import error.UnicamenteObjetivoNoAlucinacionException;
import error.UnicamenteObjetivoPropioException;
import error.UnicamenteObjetivoUnidadException;
import ficha.Ficha;
import ficha.TipoDeFicha;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.CoordenadaUtil;
import tablero.Direccion;
import tablero.ITablero;

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


    /**
     * @throws UnicamenteObjetivoPropioException
     */
    @Override
    protected void verificarObjetivo(Ficha ficha, Coordenada3d objetivo) {
        Ficha fichaObjetivo = ficha.tablero().getFicha(objetivo);

        if (!fichaObjetivo.propietario().equals(ficha.propietario())) {
            // TODO test para probar que solamente se puede aplicar a ficha del mismo jugador
            throw new UnicamenteObjetivoPropioException();
        }
        if (!fichaObjetivo.es(TipoDeFicha.UNIDAD)) {
            throw new UnicamenteObjetivoUnidadException();
        }
        if (fichaObjetivo.es(TipoDeFicha.ALUCINACION)) {
            throw new UnicamenteObjetivoNoAlucinacionException();
        }
    }

    @Override
    protected void aplicar(Ficha ficha, Coordenada3d objetivo) {
        final Set<Coordenada> casillasVecinas = CoordenadaUtil.areaDeCoordenadas(objetivo, 2);
        for (Coordenada coordenada: casillasVecinas) {
            if (ficha.tablero().hayEspacio(new Coordenada3d(coordenada, objetivo.z))) {
                Ficha fichaCopiar = ficha.tablero().getFicha(objetivo);
                this.insertarEspectro(fichaCopiar, coordenada);
                return;
            }
        }

        throw new FichaSobreOtraFichaException();
    }

    public void insertarEspectro(Ficha fichaCopiar, Coordenada lugar) {
        Ficha alucinacion = fichaCopiar.espectro();
        alucinacion.setBasico(alucinacion.propietario(), alucinacion.tablero(), lugar);
        alucinacion.ponerEnJuego();
    }

}

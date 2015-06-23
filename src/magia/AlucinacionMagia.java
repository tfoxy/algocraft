package magia;

import error.FichaSobreOtraFichaException;
import error.UnicamenteObjetivoNoAlucinacionException;
import error.UnicamenteObjetivoPropioException;
import error.UnicamenteObjetivoUnidadException;
import ficha.Ficha;
import ficha.TipoDeFicha;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.CoordenadaUtil;

import java.util.Set;

/**
 * Es capaz de crear 2 copias de una propia unidad que son
 * simples “copias falsas” Son unidades que simulan ser unidades propias
 * pero al atacar no causan daño. Se crean para que reciban daño.
 * Los enemigos no pueden distinguir una unidad alucinada de una real.
 * Cuesta 100 de energía.
 * Las unidades alucinadas ​NO​ tienen vida, solo escudo.
 */
public class AlucinacionMagia extends Magia {
    private static final int RADIO = 1;

    public AlucinacionMagia() {
        super("Alucinación", 100, 4);
    }


    private void verificarObjetivo(Ficha objetivo, Ficha caster) {
        if (!objetivo.propietario().equals(caster.propietario())) {
            throw new UnicamenteObjetivoPropioException();
        }
        if (!objetivo.es(TipoDeFicha.UNIDAD)) {
            throw new UnicamenteObjetivoUnidadException();
        }
        if (objetivo.es(TipoDeFicha.ALUCINACION)) {
            throw new UnicamenteObjetivoNoAlucinacionException();
        }
    }

    @Override
    protected void aplicar(Ficha caster, Coordenada3d objetivo) {
        Ficha fichaObjetivo = caster.tablero().getFicha(objetivo);

        verificarObjetivo(fichaObjetivo, caster);

        final Set<Coordenada> casillasVecinas = CoordenadaUtil.areaDeCoordenadas(objetivo, RADIO);
        for (Coordenada coordenada: casillasVecinas) {
            Coordenada3d coord3d = new Coordenada3d(coordenada, objetivo.z);
            if (fichaObjetivo.puedoReemplazarFichaEnTablero(coord3d)) {
                this.insertarEspectro(fichaObjetivo, coordenada);
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

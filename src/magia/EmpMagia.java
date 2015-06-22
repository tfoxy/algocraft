package magia;

import ficha.Ficha;
import ficha.TipoDeFicha;
import tablero.Coordenada3d;
import tablero.CoordenadaUtil;
import tablero.ITablero;

import java.util.List;

/**
 * Tira un misil que al impactar en un radio, causa que las ​unidades enemigas
 * pierdan su energía (para las unidades mágicas)
 * o su escudo en el caso de los protoss.
 * Cuesta 100 energía.
 * El EMP NO afecta a los edificios.
 */
public class EmpMagia extends Magia {

    private static final int RANGO = 1;

    public EmpMagia() {
        super(100, 6);
    }

    @Override
    protected void aplicar(Ficha caster, Coordenada3d objetivo) {
        ITablero mapa = caster.tablero();
        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(mapa, objetivo, RANGO);

        for (Ficha ficha: fichas) {
            if (ficha.es(TipoDeFicha.UNIDAD)) {
                ficha.barras().aplicarEmp();
            }
        }
    }
}

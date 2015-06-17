package ficha.natural.recurso;

import ficha.FuenteDeRecurso;
import ficha.TipoDeFicha;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        this(GAS_POR_DEFECTO);
    }

    public Volcan(int gas) {
        super(0, gas);

        nombre = "Volcán";
        this.tipoDeFicha.add(TipoDeFicha.VOLCAN);
    }

}


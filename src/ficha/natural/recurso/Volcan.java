package ficha.natural.recurso;

import ficha.FuenteDeRecurso;
import ficha.TipoDeFicha;

import java.awt.Color;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;
    private static final Color COLOR_VERDE_NEON = new Color(57, 255, 20);

    public Volcan() {
        this(GAS_POR_DEFECTO);
    }

    public Volcan(int gas) {
        super(0, gas);

        nombre = "Volcán";
        this.tipoDeFicha.add(TipoDeFicha.VOLCAN);
    }

    @Override
    public Color miColor() {
        return COLOR_VERDE_NEON;
    }

}


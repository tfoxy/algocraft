package ficha.natural.recurso;

import ficha.FuenteDeRecurso;
import ficha.TipoDeFicha;
import juego.Recursos;

import java.awt.Color;

public class NodoMineral extends FuenteDeRecurso {

    private static final int MINERALES_POR_DEFECTO = 1500;

    public NodoMineral() {
        this(MINERALES_POR_DEFECTO);
    }

    public NodoMineral(int mineral) {
        super(mineral, 0);

        nombre = "Nodo Mineral";
        this.tipoDeFicha.add(TipoDeFicha.MINERAL);
    }

    @Override
    public Color miColor() {
        return Color.CYAN;
    }
}

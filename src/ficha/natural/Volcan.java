package ficha.natural;

import ficha.FuenteDeRecurso;
import juego.Recursos;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        this(GAS_POR_DEFECTO);
        tipoDeFuenteDeRecursos = "Volcan";
        coste = new Recursos(0, 0, 0);
    }

    public Volcan(int cristal, int gas) {
        super(cristal, gas);

        tipoDeFuenteDeRecursos = "Volcan";
        coste = new Recursos(0, 0, 0);
    }
    
    public Volcan(int gas) {
        super(0, gas);

        tipoDeFuenteDeRecursos = "Volcan";
        coste = new Recursos(0, 0, 0);
    }
    
}


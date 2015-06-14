package ficha.natural;

import ficha.FuenteDeRecurso;
import juego.Recursos;
import tablero.Coordenada;
import tablero.Tablero;

public class NodoMineral extends FuenteDeRecurso {

    private static final int MINERALES_POR_DEFECTO = 1500;

    public NodoMineral() {
        this(MINERALES_POR_DEFECTO);
        tipoDeFuenteDeRecursos = "Mineral";
        coste = new Recursos(0, 0, 0);
    }

    public NodoMineral(int mineral) {
        super(mineral, 0);

        tipoDeFuenteDeRecursos = "Mineral";
        coste = new Recursos(0, 0, 0);
    }

    public NodoMineral(int mineral, int gas) {
        super(mineral, gas);

        tipoDeFuenteDeRecursos = "Mineral";
        coste = new Recursos(0, 0, 0);
    }
}

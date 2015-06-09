package ficha.natural;

import error.NoSePuedeCrearFicha;
import ficha.FuenteDeRecurso;
import juego.Recursos;
import tablero.Coordenada;
import tablero.Tablero;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        this(GAS_POR_DEFECTO);
        tipoDeFuenteDeRecursos = "Volcan";
    }

    public Volcan(int gas) {
        super(gas);

        tipoDeFuenteDeRecursos = "Volcan";
    }

    @Override
    public Recursos recursosVirgenes() {
        return new Recursos(0, cantidadDeRecursos);
    };

}

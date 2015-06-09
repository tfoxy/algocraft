package ficha.natural;

import error.NoSePuedeCrearFicha;
import estrategia.ficha.EstrategiaConsturccion;
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

    public void PonerEnJuego() {
    	estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }
}

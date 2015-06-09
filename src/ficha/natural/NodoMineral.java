package ficha.natural;

import ficha.FuenteDeRecurso;
import juego.Recursos;
import tablero.Coordenada;
import tablero.Tablero;

public class NodoMineral extends FuenteDeRecurso {

    private static final int MINERALES_POR_DEFECTO = 1500;

    public NodoMineral() {
        this(MINERALES_POR_DEFECTO);
    }

    public NodoMineral(int mineral) {
        super(mineral);
    }

    @Override
    public Recursos recursosVirgenes() {
        return new Recursos(cantidadDeRecursos, 0);
    };


}

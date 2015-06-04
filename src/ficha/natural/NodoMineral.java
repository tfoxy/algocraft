package ficha.natural;

import ficha.FuenteDeRecurso;
import tablero.Coordenada;
import tablero.Tablero;

public class NodoMineral extends FuenteDeRecurso {

    private static final int MINERALES_POR_DEFECTO = 1500;

    public NodoMineral() {
        super(MINERALES_POR_DEFECTO);
    }

    public NodoMineral(int mineral) {
        super(mineral);
    }

    public NodoMineral(int mineral, Tablero mapa, Coordenada lugar) {
        this(mineral);

        mapa.insertar(lugar, this);
    }

    @Override
    public void pasarTurno() {
        // TODO implementar
    }

    @Override
    public void muerete() {
        // TODO implementar
    }

}

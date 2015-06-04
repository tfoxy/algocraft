package ficha.natural;

import error.NoSePuedeCrearFicha;
import ficha.FuenteDeRecurso;
import tablero.Coordenada;
import tablero.Tablero;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        super(GAS_POR_DEFECTO);
    }

    public Volcan(int gas) {
        super(gas);
    }

    public Volcan(int gas, Coordenada lugar, Tablero mapa) {
        this(gas);
        if (!(mapa.hayEspacioTerreste(lugar))) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
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

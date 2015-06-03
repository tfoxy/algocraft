package ficha;

import Ficha.FichaTerrestre;

public class FichaEspacial implements FichaTerrestre {
    @Override
    public boolean EstasVacia() {
        return false;
    }

    @Override
    public void PasarTurno() {

    }

    @Override
    public void Muerete() {

    }
}

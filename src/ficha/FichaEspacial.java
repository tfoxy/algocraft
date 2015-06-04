package ficha;

public class FichaEspacial implements FichaTerrestre {
    @Override
    public boolean estaVacia() {
        return false;
    }

    @Override
    public void pasarTurno() {
        // noop
    }

    @Override
    public void muerete() {
        // noop
    }
}

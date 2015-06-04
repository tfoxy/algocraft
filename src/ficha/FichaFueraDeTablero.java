package ficha;

public class FichaFueraDeTablero implements FichaTerrestre, FichaAerea {
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

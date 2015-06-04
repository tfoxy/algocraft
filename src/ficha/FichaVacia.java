package ficha;

public class FichaVacia implements Ficha {

    @Override
    public boolean estaVacia() {
        return true;
    }

    @Override
    public void pasarTurno() {
        // No hacer nada
    }

    @Override
    public void muerete() {
        // No hacer nada
    }

}

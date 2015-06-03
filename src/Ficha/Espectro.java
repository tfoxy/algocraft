package Ficha;

import Jugador.TablaJugador;

public class Espectro extends Unidad implements FichaAerea {

    public Espectro(TablaJugador jugador) {
        Propetario = jugador;
    }

    @Override
    public void PasarTurno() {
        // TODO implementar
    }

    @Override
    public void Muerete() {
        // TODO implementar
    }
}

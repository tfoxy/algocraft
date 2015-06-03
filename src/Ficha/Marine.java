package Ficha;

import Jugador.TablaJugador;

public class Marine extends Unidad implements FichaTerrestre {

    public Marine(TablaJugador jugador) {
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

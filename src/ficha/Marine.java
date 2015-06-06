package ficha;

import jugador.TablaJugador;

public class Marine extends Unidad implements FichaTerrestre {

    vida = 40;
    coste = new Recursos(50, 0, 1))
    ataque = 6;
    rangoDeAtaque = 4;
    transporteMaximo = 1;
    vision = 7;
    tiempoDeConstruccion = 3;
    movimiento = 3;


    public Marine(TablaJugador jugador) {
        super(jugador);
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

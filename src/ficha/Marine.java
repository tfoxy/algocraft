package ficha;

import jugador.Recursos;
import jugador.TablaJugador;
import stats.BarrasEscudoVidaEnergia;

public class Marine extends UnidadTerrestre {


    public Marine(TablaJugador jugador) {
        super(jugador);

        barras = new BarrasEscudoVidaEnergia(40, 0, 0);
        coste = new Recursos(50, 0, 1);
        ataqueTierra = ataqueAire = 6;
        rangoDeAtaqueTierra = rangoDeAtaqueAire = 4;
        // TODO transporteMaximo = 1;
        vision = 7;
        tiempoDeConstruccion = 3;
        movimiento = 3;
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

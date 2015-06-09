package ficha;

import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Marine extends UnidadTerrestre {

    private static final BarrasEscudoVidaEnergia.Builder barrasBuilder =
            new BarrasEscudoVidaEnergia.Builder()
            .vida(40);

    public Marine() {
        barras = barrasBuilder.build();
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

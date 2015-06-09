package ficha;

import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Marine extends UnidadTerrestre {

    private static final BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
            new BarrasEscudoVidaEnergia.Builder().vida(40);

    public Marine() {
        barras = BARRAS_BUILDER.build();
        coste = new Recursos(50, 0, 1);
        ataqueTierra = ataqueAire = 6;
        rangoDeAtaqueTierra = rangoDeAtaqueAire = 4;
        // TODO transporteMaximo = 1;
        vision = 7;
        tiempoDeConstruccion = 3;
        movimiento = movimientoMaximo = 3;
    }
}

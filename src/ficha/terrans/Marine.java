package ficha.terrans;

import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;

public class Marine extends UnidadTerrestre {

    private static final BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
            new BarrasEscudoVidaEnergia.Builder().vida(40);

    public Marine() {
        nombre = "Marine";
        barras = BARRAS_BUILDER.build();
        coste = new Recursos(50, 0, 1);
        ataqueTierra = ataqueAire = new Ataque(6, 4);
        vision = 7;
        turnosParaCrear = 3;
        movimiento = movimientoMaximo = 3;
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        ocupacionEnTransporte = 1;
    }
}

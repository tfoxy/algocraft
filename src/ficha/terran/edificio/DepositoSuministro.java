package ficha.terran.edificio;

import ficha.CasaTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class DepositoSuministro extends CasaTerrestre {

    public DepositoSuministro() {
        nombre = "Depósito Suministro";
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        barras = new BarrasEscudoVidaEnergia(500);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        poblacionQueDa = 5;
    }

}

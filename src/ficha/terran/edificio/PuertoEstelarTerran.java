package ficha.terran.edificio;

import ficha.EdificioTerrestre;
import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class PuertoEstelarTerran extends EdificioTerrestre {
    public PuertoEstelarTerran() {
        nombre = "Puerto Estelar";
        coste = new Recursos(150, 100);
        turnosParaCrear = 10;
        barras = new BarrasEscudoVidaEnergia(1300);
        tipoDeFicha.add(TipoDeFicha.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.FABRICA);
        tecnologiasQueDa.add(Tecnologia.PUERTO_ESTELAR);
    }
}

package ficha.terran.edificio;

import ficha.EdificioTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Fabrica extends EdificioTerrestre {
    public Fabrica() {
        nombre = "Fábrica";
        coste = new Recursos(200, 100);
        turnosParaCrear = 12;
        barras = new BarrasEscudoVidaEnergia(1250);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.BARRACA);
        tecnologiasQueDa.add(Tecnologia.FABRICA);
    }
}

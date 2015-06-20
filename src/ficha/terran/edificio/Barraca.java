package ficha.terran.edificio;

import ficha.EdificioTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Barraca extends EdificioTerrestre {
    public Barraca() {
        nombre = "Barraca";
        coste = new Recursos(150, 0);
        turnosParaCrear = 12;
        barras = new BarrasEscudoVidaEnergia(1000);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasQueDa.add(Tecnologia.BARRACA);
    }
}

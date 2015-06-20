package ficha.terran.edificio;

import ficha.EdifcioDeRecursosTerrestre;
import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Refineria extends EdifcioDeRecursosTerrestre {
    public Refineria() {
        nombre = "Refinería";
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        barras = new BarrasEscudoVidaEnergia(750);
        tipoDeFicha.add(TipoDeFicha.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);

        recursosExtraidosPorTurno = new Recursos(0, 10);

        tipoDeFichaNecesaria = TipoDeFicha.VOLCAN;
    }
}

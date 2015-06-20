package ficha.terran.edificio;

import ficha.EdifcioDeRecursosTerrestre;
import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class CentroDeMineral extends EdifcioDeRecursosTerrestre {
    public CentroDeMineral() {
        nombre = "Centro de mineral";
        coste = new Recursos(50, 0);
        turnosParaCrear = 4;
        barras = new BarrasEscudoVidaEnergia(500);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);

        recursosExtraidosPorTurno = new Recursos(10, 0);

        tipoDeFichaNecesaria = TipoDeFicha.MINERAL;
    }
}

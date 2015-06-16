package ficha.protoss.edificio;


import ficha.EdifcioDeRecursosTerrestre;
import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class NexoMineral extends EdifcioDeRecursosTerrestre {

    public NexoMineral() {
        nombre = "Nexo mineral";
        coste = new Recursos(50, 0);
        turnosParaCrear = 4;
        barras = new BarrasEscudoVidaEnergia(250, 250);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);

        recursosExtraidosPorTurno = new Recursos(10, 0);

        tipoDeFichaNecesaria = TipoDeFicha.MINERAL;
    }

}

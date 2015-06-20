package ficha.protoss.edificio;


import ficha.EdifcioDeRecursosTerrestre;
import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Asimilador extends EdifcioDeRecursosTerrestre {

    public Asimilador() {
        nombre = "Asimilador";
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        barras = new BarrasEscudoVidaEnergia(450, 450);
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);

        recursosExtraidosPorTurno = new Recursos(0, 10);

        tipoDeFichaNecesaria = TipoDeFicha.VOLCAN;
    }

}

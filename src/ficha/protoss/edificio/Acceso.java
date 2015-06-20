package ficha.protoss.edificio;

import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import ficha.EdificioTerrestre;

public class Acceso extends EdificioTerrestre {

    public Acceso() {
        nombre = "Acceso";
        coste = new Recursos(150, 0);
        turnosParaCrear = 8;
        barras = new BarrasEscudoVidaEnergia(500, 500);
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasQueDa.add(Tecnologia.ACCESO);
    }


}

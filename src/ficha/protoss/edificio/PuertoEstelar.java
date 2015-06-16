package ficha.protoss.edificio;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import ficha.EdificioTerrestre;

public class PuertoEstelar extends EdificioTerrestre {

    public PuertoEstelar() {
        nombre = "PuertoEstelar";
        coste = new Recursos(150, 150);
        turnosParaCrear = 10;
        barras = new BarrasEscudoVidaEnergia(600, 600);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasQueDa.add(Tecnologia.PUERTO_ESTELAR);
    }


}
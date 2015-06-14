package ficha.protoss.edificios;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.EdificioTerrestre;

public class Acceso extends EdificioTerrestre {

    public Acceso() {
        nombre = "Acceso";
        coste = new Recursos(150, 0);
        tiempoDeConstruccion = 8;
        barras = new BarrasEscudoVidaEnergia(500, 500);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasQueDa.add(Tecnologia.ACCESO);
        estoyVacio = false;
    }



}

package ficha;


import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class NexoMineral extends EdificioTerrestre {

    public NexoMineral() {
        nombre = "Nexo mineral";
        coste = new Recursos(50, 0);
        // TODO agregar tiempo construccion
        barras = new BarrasEscudoVidaEnergia(250, 250);
    }
}

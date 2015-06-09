package ficha.protoss;


import ficha.EdificioTerrestre;
import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class NexoMineral extends EdificioTerrestre {

    public NexoMineral() {
        nombre = "Nexo mineral";
        coste = new Recursos(50, 0);
        // TODO agregar tiempo construccion: 4
        barras = new BarrasEscudoVidaEnergia(250, 250);
    }

    public Recursos extraer() {
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    };
}

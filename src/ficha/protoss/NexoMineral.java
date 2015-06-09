package ficha.protoss;


import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class NexoMineral extends EdifcioDeRecusosTerrestre {

    public NexoMineral() {
        nombre = "Nexo mineral";
        coste = new Recursos(50, 0);
        // TODO agregar tiempo construccion: 4
        barras = new BarrasEscudoVidaEnergia(250, 250);
    }

    @Override
    public Recursos extraer() {
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    };
}

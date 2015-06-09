package ficha;


import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Asimilador extends EdifcioDeRecusosTerrestre {

    public Asimilador() {
        nombre = "Nexo mineral";
        coste = new Recursos(100, 0);
        // TODO agregar tiempo construccion: 6
        barras = new BarrasEscudoVidaEnergia(450, 450);
    }

    public Recursos extraer() {
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    };
}

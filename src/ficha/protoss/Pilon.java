package ficha.protoss;

import ficha.CasaTerrestre;
import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Pilon extends CasaTerrestre {

    public Pilon() {
        nombre = "Pilón";
        coste = new Recursos(100, 0);
        // TODO agregar tiempo construccion: 5
        barras = new BarrasEscudoVidaEnergia(300, 300);
    }
}

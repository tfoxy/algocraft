package ficha.protoss;

import java.util.ArrayList;
import java.util.List;

import ficha.CasaTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Pilon extends CasaTerrestre {

    public Pilon() {
        nombre = "Pilón";
        coste = new Recursos(100, 0);
        // TODO agregar tiempo construccion: 5
        barras = new BarrasEscudoVidaEnergia(300, 300);
        tecnologiasNecesarias = new ArrayList<>();
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
    }
}

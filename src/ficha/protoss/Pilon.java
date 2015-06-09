package ficha.protoss;

import ficha.CasaTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Pilon extends CasaTerrestre {

    public Pilon() {
        nombre = "Pilón";
        coste = new Recursos(100, 0);
        tiempoDeConstruccion = 5;
        barras = new BarrasEscudoVidaEnergia(300, 300);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;
    }
    
}

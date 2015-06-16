package ficha.protoss.edificio;

import ficha.CasaTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Pilon extends CasaTerrestre {

    public Pilon() {
        nombre = "Pilón";
        coste = new Recursos(100, 0);
        turnosParaCrear = 5;
        barras = new BarrasEscudoVidaEnergia(300, 300);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        poblacionQueDa = 5;
    }

  /*  @Override
    public void ponerEnJuego() {
        estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.ponerEnJuego(this);
    }*/

}

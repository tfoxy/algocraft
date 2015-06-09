package ficha.protoss;

import estrategia.ficha.EstrategiaConsturccion;
import estrategia.ficha.EstrategiaFichaViva;
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
        poblacionQueDa = 5;
    }
    
    public void PonerEnJuego() {
    	estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }
    
    public void muerete() {
        estrategia.matar(this);
    }
}

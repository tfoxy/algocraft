package ficha.protoss.edificios;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.EdificioTerrestre;

public class PuertoEstelar extends EdificioTerrestre {

    public PuertoEstelar() {
        nombre = "PuertoEstelar";
        coste = new Recursos(150, 150);
        tiempoDeConstruccion = 10;
        barras = new BarrasEscudoVidaEnergia(600, 600);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;
        tecnologiasQueDa.add(Tecnologia.PUERTO_ESTELAR);
    }
    
    public void PonerEnJuego() {
    	estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }
    
    public void muerete() {
        estrategia.matar(this);
    }
	
	
}

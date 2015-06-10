package ficha.protoss.unidades;

import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.UnidadAerea;

public class NaveTransporte extends UnidadAerea {

    public NaveTransporte() {
        nombre = "NaveTransporte";
        vision = 8;
        coste = new Recursos(200, 0, 2);
        turnosParaCrear = 8;
        barras = new BarrasEscudoVidaEnergia(80,60);
        estoyVacio = false;
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
        // TODO transporte
    }
    
    public void PonerEnJuego() {
    	estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }
    
    public void muerete() {
        estrategia.matar(this);
    }
	
}

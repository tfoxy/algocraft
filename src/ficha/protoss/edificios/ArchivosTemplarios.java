package ficha.protoss.edificios;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.EdificioTerrestre;

public class ArchivosTemplarios extends EdificioTerrestre {

    public ArchivosTemplarios() {
        nombre = "ArchivosTemplarios";
        coste = new Recursos(150, 200);
        tiempoDeConstruccion = 9;
        barras = new BarrasEscudoVidaEnergia(500, 500);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;
        tecnologiasQueDa.add(Tecnologia.ARCHIVOS_TEMPLARIOS);
    }
    
    public void PonerEnJuego() {
    	estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }
    
    public void muerete() {
        estrategia.matar(this);
    }
	
}

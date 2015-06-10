package ficha.protoss.unidades;

import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.UnidadTerrestre;

public class AltoTemplario extends UnidadTerrestre {

    public AltoTemplario() {
        nombre = "AltoTemplario";
        vision = 7;
        coste = new Recursos(50, 150, 2);
        turnosParaCrear = 7;
        barras = new BarrasEscudoVidaEnergia(40, 40);
        estoyVacio = false;
        tecnologiasNecesarias.add(Tecnologia.PROTOSS.ARCHIVOS_TEMPLARIOS); //agregar AccesoDespues. Pero ver que no rompa uni
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
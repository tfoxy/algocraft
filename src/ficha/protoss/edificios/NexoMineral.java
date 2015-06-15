package ficha.protoss.edificios;


import ficha.EdifcioDeRecusosTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class NexoMineral extends EdifcioDeRecusosTerrestre {

    public NexoMineral() {
        nombre = "Nexo mineral";
        coste = new Recursos(50, 0);
        // TODO agregar tiempo construccion: 4
        barras = new BarrasEscudoVidaEnergia(250, 250);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
    }

    @Override
    public Recursos extraer() {
        // TODO Auto-generated method stub
        return null;
    }

}

package ficha.protoss.edificio;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import ficha.EdificioTerrestre;

public class ArchivosTemplarios extends EdificioTerrestre {

    public ArchivosTemplarios() {
        nombre = "Archivos Templarios";
        coste = new Recursos(150, 200);
        turnosParaCrear = 9;
        barras = new BarrasEscudoVidaEnergia(500, 500);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
        tecnologiasQueDa.add(Tecnologia.ARCHIVOS_TEMPLARIOS);
    }


}

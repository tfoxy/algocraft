package ficha.protoss.edificios;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import ficha.EdificioTerrestre;

public class ArchivosTemplarios extends EdificioTerrestre {

    public ArchivosTemplarios() {
        nombre = "ArchivosTemplarios";
        coste = new Recursos(150, 200);
        turnosParaCrear = 9;
        barras = new BarrasEscudoVidaEnergia(500, 500);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasQueDa.add(Tecnologia.ARCHIVOS_TEMPLARIOS);
        estoyVacio = false;
    }



}

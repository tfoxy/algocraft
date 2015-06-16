package ficha.protoss.unidad;

import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;
import ficha.UnidadTerrestre;

public class AltoTemplario extends UnidadTerrestre {

    public AltoTemplario() {
        nombre = "Alto Templario";
        vision = 7;
        coste = new Recursos(50, 150, 2);
        turnosParaCrear = 7;
        barras = new BarrasEscudoVidaEnergia(40, 40, 200, 50, 15);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.ARCHIVOS_TEMPLARIOS);
        ocupacionEnTransporte = 2;
    }


}
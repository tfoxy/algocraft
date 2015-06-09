package ficha.protoss;


import java.util.ArrayList;

import ficha.EdifcioDeRecusosTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Asimilador extends EdifcioDeRecusosTerrestre {

    public Asimilador() {
        nombre = "Nexo mineral";
        coste = new Recursos(100, 0);
        tiempoDeConstruccion = 6;
        barras = new BarrasEscudoVidaEnergia(450, 450);
        
        tecnologiasNecesarias = new ArrayList<>();
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;
        
        tipoDeFuenteDeRecursos = "Volcan";
    }

    public Recursos extraer() { //esto jode al polimorfismo. Seria emjor que pida que recursos extrear.
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    };
}

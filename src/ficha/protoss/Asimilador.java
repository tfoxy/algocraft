package ficha.protoss;


import java.util.ArrayList;

import estrategia.ficha.EstrategiaConsturccion;
import ficha.EdifcioDeRecusosTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Asimilador extends EdifcioDeRecusosTerrestre {

    public Asimilador() {
        nombre = "Asimilador";
        coste = new Recursos(100, 0);
        tiempoDeConstruccion = 6;
        barras = new BarrasEscudoVidaEnergia(450, 450);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;

        tipoDeFuenteDeRecursos = "Volcan";
        tipoDeFuenteDeRecursosQueNecesito = "Volcan";
    }

    @Override
    public Recursos extraer() { //esto jode al polimorfismo. Seria emjor que pida que recursos extrear.
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    }
}

package ficha.terran.unidad;

import ficha.TipoDeFicha;
import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;

public class Golliat extends UnidadTerrestre {

    public Golliat() {
        barras = new BarrasEscudoVidaEnergia(200);
        coste = new Recursos(100, 50, 2);
        ataqueTierra = new Ataque(12, 6);
        ataqueAire = new Ataque(10, 5);
        vision = 8;
        movimientoMaximo = 4;
        tipoDeFicha.add(TipoDeFicha.TERRAN);
        tipoDeFicha.add(TipoDeFicha.AVANZADA);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.FABRICA);
        ocupacionEnTransporte = 2;
    }
}

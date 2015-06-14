package ficha.terrans.unidad;

import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;

public class Golliat extends UnidadTerrestre{

    public Golliat() {
        barras = new BarrasEscudoVidaEnergia(200);
        coste = new Recursos(100, 50, 2);
        ataqueTierra = new Ataque(12, 6);
        ataqueAire = new Ataque(10, 5);
        vision = 8;
        movimiento = movimientoMaximo = 4;
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        // TODO tecnologiasNecesarias
        // TODO
        ocupacionEnTransporte = 2;
    }
}

package ficha.terrans;

import ficha.UnidadAerea;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;

public class Espectro extends UnidadAerea {

    public Espectro() {
        nombre = "Espectro";
        barras = new BarrasEscudoVidaEnergia(120);
        coste = new Recursos(150, 100, 2);
        movimiento = movimientoMaximo = 3;
        ataqueTierra = new Ataque(8, 5);
        ataqueAire = new Ataque(20, 5);
        // TODO transporte
        vision = 7;
        turnosParaCrear = 8;
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
    }
}

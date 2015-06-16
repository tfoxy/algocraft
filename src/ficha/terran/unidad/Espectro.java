package ficha.terran.unidad;

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
        movimientoMaximo = 3;
        ataqueTierra = new Ataque(8, 5);
        ataqueAire = new Ataque(20, 5);
        vision = 7;
        turnosParaCrear = 8;
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
    }
}

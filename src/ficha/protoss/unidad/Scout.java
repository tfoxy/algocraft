package ficha.protoss.unidad;

import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import ficha.UnidadAerea;

public class Scout extends UnidadAerea {

    public Scout() {
        nombre = "Scout";
        vision = 8;
        coste = new Recursos(300, 150, 3);
        turnosParaCrear = 9;
        barras = new BarrasEscudoVidaEnergia(150, 100);
        ataqueTierra = new Ataque(8, 4);
        ataqueAire = new Ataque(14, 4);
        movimientoMaximo = 3;
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
    }


}

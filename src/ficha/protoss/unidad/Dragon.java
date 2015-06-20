package ficha.protoss.unidad;

import ficha.TipoDeFicha;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import ficha.UnidadTerrestre;

public class Dragon extends UnidadTerrestre {

    public Dragon() {
        nombre = "Dragon";
        vision = 8;
        coste = new Recursos(125, 50, 2);
        turnosParaCrear = 6;
        barras = new BarrasEscudoVidaEnergia(100, 80);
        movimientoMaximo = 3;
        ataqueTierra = ataqueAire = new Ataque(20, 4);
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tipoDeFicha.add(TipoDeFicha.AVANZADA);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.ACCESO);
        ocupacionEnTransporte = 4;
    }


}

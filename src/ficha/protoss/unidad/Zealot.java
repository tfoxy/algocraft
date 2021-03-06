package ficha.protoss.unidad;


import ficha.TipoDeFicha;
import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;

public class Zealot extends UnidadTerrestre {

    public Zealot() {
        nombre = "Zealot";
        vision = 7;
        coste = new Recursos(100, 0, 2);
        turnosParaCrear = 4;
        barras = new BarrasEscudoVidaEnergia(100, 60);
        ataqueTierra = new Ataque(8, 1);
        movimientoMaximo = 3;
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tipoDeFicha.add(TipoDeFicha.BASICA);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.ACCESO);
        ocupacionEnTransporte = 2;
    }


}

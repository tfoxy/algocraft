package ficha.protoss.unidad;

import ficha.TipoDeFicha;
import ficha.TransporteAereo;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class NaveTransporteProtoss extends TransporteAereo {

    public NaveTransporteProtoss() {
        super(8);

        nombre = "Nave Transporte";
        vision = 8;
        coste = new Recursos(200, 0, 2);
        turnosParaCrear = 8;
        barras = new BarrasEscudoVidaEnergia(80, 60);
        movimientoMaximo = 6;
        tipoDeFicha.add(TipoDeFicha.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
    }


}

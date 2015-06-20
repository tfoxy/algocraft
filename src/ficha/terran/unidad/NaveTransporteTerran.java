package ficha.terran.unidad;

import ficha.TipoDeFicha;
import ficha.TransporteAereo;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class NaveTransporteTerran extends TransporteAereo {

    public NaveTransporteTerran() {
        super(8);

        nombre = "Nave Transporte";
        vision = 8;
        movimientoMaximo = 6;
        coste = new Recursos(100, 100, 2);
        turnosParaCrear = 7;
        barras = new BarrasEscudoVidaEnergia(200);
        tipoDeFicha.add(TipoDeFicha.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
    }

}

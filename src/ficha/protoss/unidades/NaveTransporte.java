package ficha.protoss.unidades;

import ficha.TransporteAereo;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class NaveTransporte extends TransporteAereo {

    public NaveTransporte() {
        super(8);

        nombre = "Nave Transporte";
        vision = 8;
        coste = new Recursos(200, 0, 2);
        turnosParaCrear = 8;
        barras = new BarrasEscudoVidaEnergia(80, 60);
        estoyVacio = false;
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
        // TODO transporte
    }


}

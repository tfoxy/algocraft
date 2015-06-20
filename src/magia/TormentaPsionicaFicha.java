package magia;

import ficha.Ficha;
import ficha.FichaAerea;
import tablero.CoordenadaUtil;

import java.util.List;

public class TormentaPsionicaFicha extends FichaAerea {

    private static final int RANGO = 1;
    private static final int DANIO = 100;
    private static final int DURACION = 2;

    private int duracionRestante;


    public TormentaPsionicaFicha() {
        duracionRestante = DURACION;
    }


    @Override
    public void pasarTurno() {
        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(tablero, coordenada, RANGO);

        for (Ficha ficha: fichas) {
            ficha.sufrirDanio(DANIO);
        }

        duracionRestante--;

        if (duracionRestante <= 0) {
            propietario.perder(this);
        }
    }

}

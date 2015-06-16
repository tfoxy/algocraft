package magia;

import ficha.Ficha;
import ficha.FichaAerea;
import tablero.CoordenadaUtil;

import java.util.List;

public class TormentaPsionica extends FichaAerea {

    private static final int RANGO = 1;
    private static final int DANIO = 100;

    private int duracion;


    public TormentaPsionica() {
        duracion = 2;
    }


    @Override
    public void pasarTurno() {
        duracion -= 1;

        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(tablero, coordenada, RANGO);

        for (Ficha ficha: fichas) {
            ficha.sufrirDanio(DANIO);
        }

        if (duracion == 0) {
            propietario.perder(this);
        }
    }

}

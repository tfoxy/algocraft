package ficha.magia;

import ficha.Ficha;
import ficha.FichaCelestial;
import ficha.TipoDeFicha;
import tablero.CoordenadaUtil;

import java.awt.Color;
import java.util.List;

public class TormentaPsionicaFicha extends FichaCelestial {

    private static final int RADIO = 1;
    private static final int DANIO = 100;
    private static final int DURACION = 2;
    private static final Color COLOR = new Color(135, 174, 222);

    private int duracionRestante;


    public TormentaPsionicaFicha() {
        duracionRestante = DURACION;
        this.tipoDeFicha.add(TipoDeFicha.MAGICA);
    }

    @Override
    public void pasarTurno() {
        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(this, RADIO);

        for (Ficha ficha: fichas) {
            ficha.sufrirDanio(DANIO);
        }

        duracionRestante--;

        if (duracionRestante <= 0) {
            this.muerete();
        }
    }


    @Override
    public Color miColor() {
        return COLOR;
    }
}

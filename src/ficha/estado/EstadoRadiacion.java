package ficha.estado;

import ficha.Ficha;
import tablero.CoordenadaUtil;

import java.util.List;

public class EstadoRadiacion implements EstadoDeFicha {
    private static final int RADIO = 1;
    private static final int DANIO = 20;

    public static final EstadoRadiacion INSTANCE = new EstadoRadiacion();

    private EstadoRadiacion() {
        // noop
    }

    @Override
    public void aplicarEn(Ficha ficha) {
        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(ficha, RADIO);

        for (Ficha fichaEnArea: fichas) {
            fichaEnArea.sufrirDanio(DANIO);
        }
    }
}

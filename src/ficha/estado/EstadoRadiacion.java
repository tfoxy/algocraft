package ficha.estado;

import ficha.Ficha;
import tablero.CoordenadaUtil;

import java.util.List;

public class EstadoRadiacion implements EstadoDeFicha {
    private static final int RANGO = 1;
    private static final int DANIO = 20;

    @Override
    public void aplicarEn(Ficha ficha) {
        List<Ficha> fichas = CoordenadaUtil.fichasEnArea(ficha, RANGO);

        for (Ficha fichaEnArea: fichas) {
            fichaEnArea.sufrirDanio(DANIO);
        }
    }
}

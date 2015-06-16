package ficha;

import stats.Ataque;
import tablero.Altura;

public class FichaTerrestre extends Ficha {

    public FichaTerrestre() {
        tipoDeFicha.add(TipoDeFicha.TERRESTRE);
    }

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueTierra;
    }

    @Override
    public int altura() {
        return Altura.TIERRA;
    }

}

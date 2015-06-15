package ficha;

import stats.Ataque;
import tablero.Altura;

public class FichaTerrestre extends Ficha {

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueTierra;
    }

    @Override
    public int altura() {
        return Altura.TIERRA;
    }

}

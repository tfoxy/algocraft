package ficha;

import stats.Ataque;
import tablero.Altura;

public class FichaAerea extends Ficha {

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueAire;
    }

    @Override
    public int altura() {
        return Altura.AIRE;
    }
}

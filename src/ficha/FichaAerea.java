package ficha;

import stats.Ataque;

public class FichaAerea extends Ficha {

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueAire;
    }

}

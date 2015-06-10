package ficha;

import juego.Juego;
import stats.Ataque;

public class FichaTerrestre extends Ficha {

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueTierra;
    }

}

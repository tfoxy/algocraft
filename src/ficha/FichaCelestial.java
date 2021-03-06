package ficha;

import stats.Ataque;
import tablero.Altura;

public class FichaCelestial extends Ficha {

    public FichaCelestial() {
        tipoDeFicha.add(TipoDeFicha.CELESTIAL);
    }

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueAire;
    }

    @Override
    public int altura() {
        return Altura.CIELO;
    }
}

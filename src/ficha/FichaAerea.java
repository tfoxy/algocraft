package ficha;

import juego.Jugador;
import stats.Ataque;
import tablero.Altura;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

public class FichaAerea extends Ficha {

    public FichaAerea() {
        tipoDeFicha.add(TipoDeFicha.AEREA);
    }

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueAire;
    }

    @Override
    public int altura() {
        return Altura.AIRE;
    }
}

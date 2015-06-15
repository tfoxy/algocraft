package ficha;

import juego.Jugador;
import stats.Ataque;
import tablero.Altura;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

public class FichaAerea extends Ficha {

    @Override
    public Ataque tipoDeAtaqueRecibido(Ficha atacante) {
        return atacante.ataqueAire;
    }

    @Override
    public int altura() {
        return Altura.AIRE;
    }

    @Override
    public void setBasico(Jugador jugador, Tablero mapa, Coordenada lugar) {
        propietario = jugador;
        tablero = mapa;
        coordenada = lugar;
        coordenada2 = new Coordenada3d(coordenada, 2);
    } //por alguna razon la solucion con super no funciona.
}

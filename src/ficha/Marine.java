package ficha;

import juego.Recursos;
import juego.Jugador;

public class Marine extends UnidadTerrestre {


    public Marine(Jugador jugador) {
        super(jugador);

        barras = new BarrasEscudoVidaEnergiaBuilder().setVidaMaxima(40).setEscudoMaximo(0).setEnergiaMaxima(0).createBarrasEscudoVidaEnergia();
        coste = new Recursos(50, 0, 1);
        ataqueTierra = ataqueAire = 6;
        rangoDeAtaqueTierra = rangoDeAtaqueAire = 4;
        // TODO transporteMaximo = 1;
        vision = 7;
        tiempoDeConstruccion = 3;
        movimiento = 3;
    }

    @Override
    public void pasarTurno() {
        // TODO implementar
    }

    @Override
    public void muerete() {
        // TODO implementar
    }
}

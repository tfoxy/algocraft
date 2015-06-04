package ficha;

import jugador.Recursos;
import jugador.TablaJugador;


public class NexoMineral extends Edificio implements FichaTerrestre {

    private Recursos coste;
    private int turnosParaCrear;

    public NexoMineral(TablaJugador propetario) {
        super(propetario);
        coste = new Recursos(50, 0);
        turnosParaCrear = 4;
    }

    @Override
    public void pasarTurno() {
        getPropietario().agregarRecursosLineales(10, 0);
    }

    @Override
    public void muerete() {
        // TODO Auto-generated method stub

    }

}

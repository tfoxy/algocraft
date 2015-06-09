package estrategia.ficha;

import tablero.Direccion;
import ficha.Ficha;
import estrategia.ficha.moduloDeEstrategias.ModuloAtacarYSerAtacado;
import estrategia.ficha.moduloDeEstrategias.ModuloMorir;


public abstract class EstrategiaFicha {

    ModuloAtacarYSerAtacado moduloAtacarYSerAtacado = new ModuloAtacarYSerAtacado();
    ModuloMorir moduloMorir = new ModuloMorir();

    public void serAtacado(int danio, Ficha ficha) {
        moduloAtacarYSerAtacado.serAtacado(danio, ficha);
    }

    public void matar(Ficha ficha) {
        moduloMorir.morir(ficha);
    }


    public void atacar(Ficha agresor, Ficha defensor) {
        //nada
    }

    public void PonerEnJuego(Ficha nueva) {
        //nada
    }

    public void intentarMovimiento(Ficha ficha, Direccion dirrecion) {
        //nada
    }

    public abstract EstrategiaFicha pasarTurno(Ficha ficha);

}

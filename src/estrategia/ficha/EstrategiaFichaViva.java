package estrategia.ficha;

import tablero.Direccion;
import estrategia.ficha.moduloDeEstrategias.ModuloEfectosDeTurno;
import estrategia.ficha.moduloDeEstrategias.ModuloMover;
import ficha.Ficha;

public class EstrategiaFichaViva extends EstrategiaFicha {

    ModuloMover moduloMover = new ModuloMover();
    ModuloEfectosDeTurno moduloEfectosDeTurno = new ModuloEfectosDeTurno();

    public void atacar(Ficha agresor, Ficha defensor) {
        moduloAtacarYSerAtacado.atacar(agresor, defensor);
    }

    public void intentarMovimiento(Ficha ficha, Direccion dirrecion) {
        moduloMover.intentarMovimiento(ficha, dirrecion);
    }

    @Override
    public EstrategiaFicha pasarTurno(Ficha ficha) {
        ficha.barras().pasarTurno();
        ficha.recuperarPuntosDeMovimiento();

        moduloEfectosDeTurno.pasarTurno(ficha);

        return this;
    }
}

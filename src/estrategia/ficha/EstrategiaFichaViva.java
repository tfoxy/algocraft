package estrategia.ficha;

import tablero.Movimiento;
import estrategia.ficha.moduloDeEstrategias.ModuloAtacarYSerAtacado;
import estrategia.ficha.moduloDeEstrategias.ModuloEfectosDeTurno;
import estrategia.ficha.moduloDeEstrategias.ModuloMover;
import ficha.Ficha;
import ficha.FichaTerrestre;

public class EstrategiaFichaViva extends EstrategiaFicha {

    ModuloMover moduloMover = new ModuloMover();
    ModuloEfectosDeTurno moduloEfectosDeTurno = new ModuloEfectosDeTurno();

    public void atacar(Ficha agresor, Ficha defensor) {
        moduloAtacarYSerAtacado.atacar(agresor, defensor);
    }

    public void intentarMovimiento(Ficha ficha, Movimiento dirrecion) {
        moduloMover.intentarMovimiento(ficha, dirrecion);
    }

    @Override
    public EstrategiaFicha pasarTurno(Ficha ficha) {
        ficha.barras().pasarTurno();
        moduloEfectosDeTurno.pasarTurno(ficha);
        return this;
    }
}

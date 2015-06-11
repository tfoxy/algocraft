package estrategia.ficha;

import tablero.Direccion;
import estrategia.ficha.moduloDeEstrategias.ModuloEfectosDeTurno;
import estrategia.ficha.moduloDeEstrategias.ModuloMover;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;

public class EstrategiaFichaViva extends EstrategiaFicha {

    ModuloMover moduloMover = new ModuloMover();
    ModuloEfectosDeTurno moduloEfectosDeTurno = new ModuloEfectosDeTurno();

    
    public boolean atacar(Ficha agresor, Ficha defensor) {
		return moduloAtacarYSerAtacado.atacar(agresor, defensor);
    }

    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
		return  moduloMover.intentarMovimiento(ficha, dirrecion); }
    public boolean intentarMovimiento(FichaTerrestre ficha, Direccion dirrecion) {
		return  moduloMover.intentarMovimiento(ficha, dirrecion); }
    public boolean intentarMovimiento(FichaAerea ficha, Direccion dirrecion) {
		return  moduloMover.intentarMovimiento(ficha, dirrecion); }
    
    @Override
    public EstrategiaFicha pasarTurno(Ficha ficha) {
        ficha.barras().pasarTurno();
        ficha.recuperarPuntosDeMovimiento();

        moduloEfectosDeTurno.pasarTurno(ficha);

        return this;
    }
}

package estrategia.ficha;

import tablero.Movimiento;
import Fichas.Ficha;
import error.NoSePuedeCrearFicha;
import estrategia.ficha.moduloDeEstrategias.ModuloAtacarYSerAtacado;
import estrategia.ficha.moduloDeEstrategias.ModuloMorir;
import estrategia.ficha.moduloDeEstrategias.ModuloMover;



public abstract class EstrategiaFicha {

	ModuloAtacarYSerAtacado moduloAtacarYSerAtacado = new ModuloAtacarYSerAtacado();
	ModuloMorir moduloMorir = new ModuloMorir();

	public void serAtacado ( int danio, Ficha ficha){
		moduloAtacarYSerAtacado.serAtacado(danio, ficha);
	}
	
	public void matar(Ficha ficha){ 
		moduloMorir.morir(ficha);
	}

	
	
	
	public void atacar ( Ficha agresor, Ficha defensor){
		//nada
	}
	
	public void  PonerEnJuego(Ficha nueva) {
		//nada
    }
    public void intentarMovimiento(Ficha ficha, Movimiento dirrecion) {
		//nada
    }
	
	public abstract EstrategiaFicha pasarTurno(Ficha ficha);

}

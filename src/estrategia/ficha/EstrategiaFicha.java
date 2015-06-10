package estrategia.ficha;

import tablero.Direccion;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;
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
    public void matar(CasaTerrestre ficha) {
        moduloMorir.morir(ficha);
    }
    public void matar(UnidadTerrestre ficha) {
        moduloMorir.morir(ficha);
    }
    public void matar(UnidadAerea  ficha) {
        moduloMorir.morir(ficha);
    }
    public void matar(EdificioTerrestre ficha) {
        moduloMorir.morir(ficha);
    }
    public void matar(EdifcioDeRecusosTerrestre ficha) {
        moduloMorir.morir(ficha);
    }

    public void atacar(Ficha agresor, Ficha defensor) {
        //nada
    }

    public void PonerEnJuego(Ficha nueva){/*nada*/}
    public void PonerEnJuego(FichaTerrestre nueva){/*nada*/}
    public void PonerEnJuego(FuenteDeRecurso nueva){/*nada*/}
    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva){/*nada*/}
    
    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
		return false; /*nada*/ }
    public boolean intentarMovimiento(FichaTerrestre ficha, Direccion dirrecion) {
		return false; /*nada*/ }
    public boolean intentarMovimiento(FichaAerea ficha, Direccion dirrecion) {
		return false; /*nada*/ }
    
    public abstract EstrategiaFicha pasarTurno(Ficha ficha);

}

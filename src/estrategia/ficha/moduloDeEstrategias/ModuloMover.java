package estrategia.ficha.moduloDeEstrategias;

import error.ErrorDePolimorfismo;
import error.JuegoException;
import error.MovimientoInsuficienteException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;


public class ModuloMover {

    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
    	throw new ErrorDePolimorfismo();
    }

    //terreste
    public boolean intentarMovimiento(FichaTerrestre ficha, Direccion direccion) {
        try {
            mover(ficha, direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover(FichaTerrestre ficha, Direccion direccion) {
        Tablero mapa = ficha.tablero();
        Coordenada ubicacion = ficha.coordenada();
        Coordenada nuevaUbicacion = ubicacion.dameCordenadaHacia(direccion);

        if (ficha.movimiento() <= 0) {
            throw new MovimientoInsuficienteException();
        }

        mapa.insertar(nuevaUbicacion, ficha);
        ficha.disminuirMovimiento();
    }


    //areo
    public boolean intentarMovimiento(FichaAerea ficha, Direccion direccion) {
        try {
            mover(ficha, direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover(FichaAerea ficha, Direccion direccion) {
        Tablero mapa = ficha.tablero();
        Coordenada ubicacion = ficha.coordenada();
        Coordenada nuevaUbicacion = ubicacion.dameCordenadaHacia(direccion);

        if (ficha.movimiento() <= 0) {
            throw new MovimientoInsuficienteException();
        }

        mapa.insertar(nuevaUbicacion, ficha);
        ficha.disminuirMovimiento();
    }

}

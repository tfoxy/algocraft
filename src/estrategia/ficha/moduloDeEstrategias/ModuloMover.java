package estrategia.ficha.moduloDeEstrategias;

import error.JuegoException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;


public class ModuloMover {

    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
        //para el polimorfismo
        return false;
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

        mapa.insertar(nuevaUbicacion, ficha);
        mapa.getCasilla(ubicacion).eliminarFichaTerrestre();
        ficha.coordenada(nuevaUbicacion);
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

        mapa.insertar(nuevaUbicacion, ficha);
        mapa.getCasilla(ubicacion).eliminarFichaAerea();
        ficha.coordenada(nuevaUbicacion);
    }

}

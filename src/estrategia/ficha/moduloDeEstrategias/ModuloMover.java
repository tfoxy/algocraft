package estrategia.ficha.moduloDeEstrategias;

import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;
import error.PosicionFueraDeLimiteException;


public class ModuloMover {
    private Tablero mapa;
    private Coordenada ubicacion;
    private Coordenada nuevaUbicacion;

    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
        //para el polimorfismo
        return false;
    }

    //terreste
    public boolean intentarMovimiento(FichaTerrestre ficha, Direccion dirrecion) {

        mapa = ficha.tablero();
        ubicacion = ficha.coordenada();
        nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMover(ficha, dirrecion)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaTerrestre());
            ficha.setCoordenada(nuevaUbicacion);
        }

        return false;
    }

    private boolean sePuedeMover(FichaTerrestre ficha, Direccion dirrecion) {

        //si se quita el private hay que re inicializar
        try {
            mapa.getCasilla(ficha.coordenada().dameCordenadaHacia(dirrecion));
        } catch (PosicionFueraDeLimiteException e) { //cosa a verificar.
            return false;
        }

        if (mapa.hayEspacioTerreste(ubicacion.dameCordenadaHacia(dirrecion))) {
            return false;
        }

        return true;
    }


    //areo
    public boolean intentarMovimiento(FichaAerea ficha, Direccion dirrecion) {

        mapa = ficha.tablero();
        ubicacion = ficha.coordenada();
        nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMover(ficha, dirrecion)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaAerea());
            ficha.setCoordenada(nuevaUbicacion);
        }
        return false;
    }

    private boolean sePuedeMover(FichaAerea ficha, Direccion dirrecion) {

        //si se quita el private hay que re inicializar

        try {
            mapa.getCasilla(ficha.coordenada().dameCordenadaHacia(dirrecion));
        } catch (PosicionFueraDeLimiteException e) { //cosa a verificar.
            return false;
        }

        return mapa.hayEspacioArreo(ubicacion.dameCordenadaHacia(dirrecion));
    }


}

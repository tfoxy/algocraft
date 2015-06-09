package estrategia.ficha.moduloDeEstrategias;

import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Casilla;
import tablero.Coordenada;
import tablero.Direccion;
import tablero.Tablero;
import error.PosicionFueraDeLimiteException;


public class ModuloMover {

    public boolean intentarMovimiento(Ficha ficha, Direccion dirrecion) {
        //para el polimorfismo
        return false;
    }

    //terreste
    public boolean intentarMovimiento(FichaTerrestre ficha, Direccion dirrecion) {

        Tablero mapa = ficha.tablero();
        Coordenada ubicacion = ficha.coordenada();
        Coordenada nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMoverEnTierra(nuevaUbicacion, mapa)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaTerrestre());
            ficha.coordenada(nuevaUbicacion);
        }

        return false;
    }

    private boolean sePuedeMoverEnTierra(Coordenada ubicacion, Tablero mapa) {
        Casilla casilla;

        try {
            casilla = mapa.getCasilla(ubicacion);
        } catch (PosicionFueraDeLimiteException e) {
            return false;
        }

        return casilla.hayEspacioTerreste();
    }


    //areo
    public boolean intentarMovimiento(FichaAerea ficha, Direccion dirrecion) {

        Tablero mapa = ficha.tablero();
        Coordenada ubicacion = ficha.coordenada();
        Coordenada nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMoverEnAire(ubicacion, mapa)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaAerea());
            ficha.coordenada(nuevaUbicacion);
        }
        return false;
    }

    private boolean sePuedeMoverEnAire(Coordenada ubicacion, Tablero mapa) {
        Casilla casilla;

        try {
            casilla = mapa.getCasilla(ubicacion);
        } catch (PosicionFueraDeLimiteException e) {
            return false;
        }

        return casilla.hayEspacioTerreste();
    }


}

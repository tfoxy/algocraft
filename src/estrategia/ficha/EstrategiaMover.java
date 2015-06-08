package estrategia.ficha;

import tablero.Coordenada;
import tablero.Movimiento;
import tablero.Tablero;
import error.PosicionFueraDeLimiteException;
import ficha.FichaAerea;
import ficha.FichaTerrestre;

//la ide es que sea implementada por otras Estrategias.
public class EstrategiaMover {


    private Tablero mapa;
    private Coordenada ubicacion;
    private Coordenada nuevaUbicacion;

    //terreste
    public boolean intentarMovimiento(FichaTerrestre ficha, Movimiento dirrecion) {

        mapa = ficha.getTablero();
        ubicacion = ficha.getCoordenada();
        nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMover(ficha, dirrecion)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaTierra());
            ficha.setCoordenada(nuevaUbicacion);
        }

        return false;
    }

    private boolean sePuedeMover(FichaTerrestre ficha, Movimiento dirrecion) {

        //si se quita el private hay que re inicializar
        try {
            mapa.getCasilla(ficha.getCoordenada().dameCordenadaHacia(dirrecion));
        } catch (PosicionFueraDeLimiteException e) //cosa a verificar.
        {
            return false;
        }

        if (mapa.hayEspacioTerreste(ubicacion.dameCordenadaHacia(dirrecion))) {
            return false;
        }

        return true;
    }


    //areo
    public boolean intentarMovimiento(FichaAerea ficha, Movimiento dirrecion) {

        mapa = ficha.getTablero();
        ubicacion = ficha.getCoordenada();
        nuevaUbicacion = ubicacion.dameCordenadaHacia(dirrecion);

        if (this.sePuedeMover(ficha, dirrecion)) {
            mapa.insertar(nuevaUbicacion, ficha);
            mapa.insertar(ubicacion, new FichaAire());
            ficha.setCoordenada(nuevaUbicacion);
        }
        return false;
    }

    private boolean sePuedeMover(FichaAerea ficha, Movimiento dirrecion) {

        //si se quita el private hay que re inicializar

        try {
            mapa.getCasilla(ficha.getCoordenada().dameCordenadaHacia(dirrecion));
        } catch (PosicionFueraDeLimiteException e) //cosa a verificar.
        {
            return false;
        }

        return mapa.hayEspacioArreo(ubicacion.dameCordenadaHacia(dirrecion));
    }


}

package estrategia.ficha.moduloDeEstrategias;

import error.ErrorDePolimorfismo;
import error.JuegoException;
import error.MovimientoInsuficienteException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;


public class ModuloMover {

    public boolean intentarMovimiento(Ficha ficha, Direccion direccion) {
        try {
            mover(ficha, direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover(Ficha ficha, Direccion direccion) {
        Tablero mapa = ficha.tablero();
        Coordenada3d ubicacion = ficha.coordenada();
        Coordenada3d nuevaUbicacion = ubicacion.dameCordenadaHacia(direccion);

        if (ficha.movimiento() <= 0) {
            throw new MovimientoInsuficienteException();
        }

        mapa.insertar(nuevaUbicacion, ficha);
        mapa.eliminarFicha(ubicacion);
        ficha.disminuirMovimiento();
    }

}

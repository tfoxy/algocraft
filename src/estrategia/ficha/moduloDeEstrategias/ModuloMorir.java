package estrategia.ficha.moduloDeEstrategias;


import ficha.*;
import ficha.EdifcioDeRecusosTerrestre;


public class ModuloMorir {

    public void morir(Ficha ficha) {
        // para permitir Polimorfismo.
    }

    public void morir(CasaTerrestre casa) {
        casa.propietario().perderPoblacionTotal(casa.poblacionQueDa());
        casa.propietario().perderFicha(casa);
        casa.tablero().getCasilla(casa.coordenada()).eliminarFichaTerrestre();
    }

    public void morir(UnidadTerrestre unidad) {
        unidad.propietario().perderPoblacionActual(unidad.coste().poblacion());
        unidad.propietario().perderFicha(unidad);
        unidad.tablero().getCasilla(unidad.coordenada()).eliminarFichaTerrestre();
    }

    public void morir(UnidadAerea unidad) {
        unidad.propietario().perderPoblacionActual(unidad.coste().poblacion());
        unidad.propietario().perderFicha(unidad);
        unidad.tablero().getCasilla(unidad.coordenada()).eliminarFichaAerea();
    }

    public void morir(EdificioTerrestre edificio) {
        edificio.propietario().perderFicha(edificio);
        edificio.tablero().getCasilla(edificio.coordenada()).eliminarFichaTerrestre();
    }

    public void morir(EdifcioDeRecusosTerrestre edificio) {
        edificio.propietario().perderFicha(edificio);
        edificio.tablero().getCasilla(edificio.coordenada()).eliminarFichaTerrestre();
        edificio.tablero().insertar(edificio.coordenada(), edificio.fuenteDeRecursos());
    }
}

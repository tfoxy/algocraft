package estrategia.ficha.moduloDeEstrategias;


import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;


public class ModuloMorir {

    public void morir(Ficha ficha) {
        ficha.propietario().perderPoblacionActual(ficha.coste().poblacion());
        ficha.propietario().perderPoblacionTotal(ficha.poblacionQueDa());
        ficha.propietario().perderFicha(ficha);
        ficha.tablero().eliminarFicha(ficha.coordenada());
    }

    // TODO quitar resto de los métodos (excepto EdifcioDeRecusosTerrestre) y ver si los tests pasan

    public void morir(CasaTerrestre casa) {
        casa.propietario().perderPoblacionTotal(casa.poblacionQueDa());
        casa.propietario().perderFicha(casa);
        casa.tablero().eliminarFichaTerrestre(casa.coordenada());
    }

    public void morir(UnidadTerrestre unidad) {
        unidad.propietario().perderPoblacionActual(unidad.coste().poblacion());
        unidad.propietario().perderFicha(unidad);
        unidad.tablero().eliminarFichaTerrestre(unidad.coordenada());
    }

    public void morir(UnidadAerea unidad) {
        unidad.propietario().perderPoblacionActual(unidad.coste().poblacion());
        unidad.propietario().perderFicha(unidad);
        unidad.tablero().eliminarFichaAerea(unidad.coordenada());
    }

    public void morir(EdificioTerrestre edificio) {
        edificio.propietario().perderFicha(edificio);
        edificio.tablero().eliminarFichaTerrestre(edificio.coordenada());
    }

    public void morir(EdifcioDeRecusosTerrestre edificio) {
        edificio.propietario().perderFicha(edificio);
        edificio.tablero().eliminarFichaTerrestre(edificio.coordenada());
        
        ModuloConstruccion modulo = new ModuloConstruccion();
        modulo.PonerEnJuego(edificio.fuenteDeRecursos());
    }
}

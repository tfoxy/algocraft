package estrategia.ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccionOP;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;

public class ExtrategiaConstrucccionOP{


    private ModuloConstruccionOP moduloConstruccion = new ModuloConstruccionOP();


    public void PonerEnJuego(Ficha nueva) {
    }
    public void PonerEnJuego(FichaTerrestre nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
    public void PonerEnJuego(FuenteDeRecurso nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
    public void PonerEnJuego(UnidadTerrestre nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
    public void PonerEnJuego(UnidadAerea  nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
    

}

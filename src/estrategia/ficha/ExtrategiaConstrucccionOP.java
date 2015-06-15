package estrategia.ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloConstruccionOP;
import ficha.*;

public class ExtrategiaConstrucccionOP {


    private ModuloConstruccionOP moduloConstruccion = new ModuloConstruccionOP();



    public void ponerEnJuego(Ficha nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
    }
/*
    public void PonerEnJuego(FichaTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void PonerEnJuego(FichaAerea nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void PonerEnJuego(FuenteDeRecurso nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }*/

    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
/*
    public void PonerEnJuego(UnidadTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void PonerEnJuego(UnidadAerea nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

*/
}

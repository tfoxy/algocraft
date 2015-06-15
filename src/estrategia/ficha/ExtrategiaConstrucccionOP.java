package estrategia.ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloConstruccionOP;
import ficha.*;

public class ExtrategiaConstrucccionOP {


    private ModuloConstruccionOP moduloConstruccion = new ModuloConstruccionOP();


    public void ponerEnJuego(Ficha nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
    }
/*
    public void ponerEnJuego(FichaTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void ponerEnJuego(FichaAerea nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void ponerEnJuego(FuenteDeRecurso nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }*/

    public void ponerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }
/*
    public void ponerEnJuego(UnidadTerrestre nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

    public void ponerEnJuego(UnidadAerea nueva) {
        moduloConstruccion.ponerEnJuego(nueva);
        nueva.propietario().agregarPoblacionTotal(nueva.poblacionQueDa());
    }

*/
}

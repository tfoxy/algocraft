package estrategia.ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import ficha.Ficha;

public class EstrategiaConsturccion extends EstrategiaFicha {

    ModuloConstruccion moduloConstruccion = new ModuloConstruccion();


    public void PonerEnJuego(Ficha nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
    }

    public EstrategiaFicha pasarTurno(Ficha ficha) {
        // TODO llenar vida de a poco
        moduloConstruccion.pasarTurno(ficha);
        if (moduloConstruccion.estaCreada(ficha)) {
            ficha.propietario().agregarPoblacionTotal(ficha.poblacionQueDa()); //esto solo hace algo en las casas.
            return new EstrategiaFichaViva();
        }
        return this;
    }
}

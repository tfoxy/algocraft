package estrategia.ficha.moduloDeEstrategias;

import jugador.Recursos;
import Fichas.EdifcioDeRecusosTerrestre;
import Fichas.Ficha;

public class ModuloEfectosDeTurno {

    public void pasarTurno(Ficha ficha) {
        //nada
    }


    public void pasarTurno(EdifcioDeRecusosTerrestre recolector) {

        if (this.sePuedeEstrear(recolector)) {
            recolector.propietario().agregarRecursos(recolector.recursosExtraidosPorTurno());
            recolector.fuenteDeRecursos().recursosVirgenes().gastaRecursos(recolector.recursosExtraidosPorTurno());
        }
    }

    private boolean sePuedeEstrear(EdifcioDeRecusosTerrestre recolector) {
        Recursos recursosAEstrear = recolector.recursosExtraidosPorTurno();
        Recursos recursosRestantes = recolector.fuenteDeRecursos().recursosVirgenes();

        if (recursosRestantes.tengoSuficientesRecursos(recursosAEstrear))
            return true;

        return false;
    }
}

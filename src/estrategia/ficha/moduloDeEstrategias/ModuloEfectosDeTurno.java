package estrategia.ficha.moduloDeEstrategias;

import ficha.EdifcioDeRecusosTerrestre;
import ficha.FuenteDeRecurso;
import juego.Recursos;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.Ficha;

public class ModuloEfectosDeTurno {

    public void pasarTurno(Ficha ficha) {
        ficha.recuperarPuntosDeMovimiento();
    }

    public void pasarTurno(EdifcioDeRecusosTerrestre recolector) {
        Recursos recursosExtraidos = recolector.extraer();

        recolector.propietario().agregarRecursos(recursosExtraidos);
    }
    // El siguiente código comentado funciona mal
    // cuando hay 5 recursos virgenes y se quiere extraer 10
    // (se extrae 10 a pesar de solamente haber 5)
    /*
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
    */
}

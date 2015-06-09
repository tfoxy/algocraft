package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.EstrategiaUnidadSoldado;
import juego.Recursos;
import juego.Jugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaConstruccionUnidadSoldado extends EstrategiaConstruccion {

    public EstrategiaConstruccionUnidadSoldado(
            Recursos coste,
            int turnosParaCrear,
            Jugador propetario,
            Tablero mapa,
            Coordenada lugar,
            ListaDeTecnologias tecnologiasNecesarias) {
        super(
                coste, turnosParaCrear, propetario,
                mapa, lugar, tecnologiasNecesarias
        );
    }

    @Override
    public EstrategiaFicha pasarTurno() {
        turnosFaltantesParaCrear = turnosFaltantesParaCrear - 1;
        if (turnosFaltantesParaCrear == 0) {
            return new EstrategiaUnidadSoldado(
                    propetario, mapa, lugar,
                    coste.poblcacionActual());
        }
        return this;
    }

    @Override
    public void morir(Ficha soldado) {
        propetario.perderPoblacionActual(coste.poblcacionActual());
        propetario.perderFicha(soldado);
        mapa.getCasilla(lugar).eliminarFichaTerrestre();
    }
}

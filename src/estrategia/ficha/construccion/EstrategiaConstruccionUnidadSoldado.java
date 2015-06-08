package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.EstrategiaUnidadSoldado;
import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import tecnologia.ListaDeTecnologias;

public class EstrategiaConstruccionUnidadSoldado extends EstrategiaConstruccion {

    public EstrategiaConstruccionUnidadSoldado(
            Recursos coste,
            int turnosParaCrear,
            TablaJugador propetario,
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
        turnosParaCrear = turnosParaCrear - 1;
        if (turnosParaCrear == 0) {
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

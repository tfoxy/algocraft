package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.EstrategiaCasa;
import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaConstruccionPilon extends EstrategiaConstruccion {

    public EstrategiaConstruccionPilon(
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
            return new EstrategiaCasa(propetario, mapa, lugar);
        }
        return this;
    }

}

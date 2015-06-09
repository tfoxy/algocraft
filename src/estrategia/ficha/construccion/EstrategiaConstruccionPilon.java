package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.EstrategiaCasa;
import juego.Recursos;
import juego.Jugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaConstruccionPilon extends EstrategiaConstruccion {

    public EstrategiaConstruccionPilon(
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
            return new EstrategiaCasa(propetario, mapa, lugar);
        }
        return this;
    }

}

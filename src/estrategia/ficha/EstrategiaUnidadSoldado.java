package estrategia.ficha;

import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaUnidadSoldado extends EstrategiaFicha {

    private TablaJugador propietario;
    private Tablero mapa;
    private Coordenada lugar;
    private int poblacionOpcupada;

    public EstrategiaUnidadSoldado(TablaJugador propietario,
                                   Tablero mapa,
                                   Coordenada lugar,
                                   int poblacionOpcupada) {
        this.propietario = propietario;
        this.mapa = mapa;
        this.lugar = lugar;
        this.poblacionOpcupada = poblacionOpcupada;
    }

    @Override
    public void morir(Ficha soldado) {
        propietario.perderPoblacionActual(poblacionOpcupada);
        propietario.perderFicha(soldado);
    }

}

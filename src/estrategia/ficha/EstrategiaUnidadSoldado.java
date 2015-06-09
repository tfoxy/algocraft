package estrategia.ficha;

import juego.Jugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaUnidadSoldado extends EstrategiaFicha {

    private Jugador propietario;
    private Tablero mapa;
    private Coordenada lugar;
    private int poblacionOpcupada;

    public EstrategiaUnidadSoldado(Jugador propietario,
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

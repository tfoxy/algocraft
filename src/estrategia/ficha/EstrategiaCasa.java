package estrategia.ficha;

import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;


public class EstrategiaCasa extends EstrategiaFicha {

    private TablaJugador propetario;
    private Tablero mapa;
    private Coordenada lugar;

    public EstrategiaCasa(TablaJugador propetario, Tablero mapa, Coordenada lugar) {
        this.propetario = propetario;
        this.mapa = mapa;
        this.lugar = lugar;
        propetario.agregarPoblacionTotal(5);

    }

    @Override
    public void morir(Ficha casa) {
        propetario.perderPoblacionTotal(5);
        propetario.perderFicha(casa);
        mapa.getCasilla(lugar).eliminarFichaTerrestre();
    }

}

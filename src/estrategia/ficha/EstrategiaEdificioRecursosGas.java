package estrategia.ficha;

import ficha.Ficha;
import ficha.natural.Volcan;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaEdificioRecursosGas extends EstrategiaFicha {

    private static final int CANTIDAD_EXTRAIDA_POR_TURNO = 10;

    private TablaJugador propetario;
    private Tablero mapa;
    private Coordenada lugar;
    private Volcan fuenteDeRecursos;


    public EstrategiaEdificioRecursosGas(TablaJugador propetario,
                                         Tablero mapa,
                                         Coordenada lugar,
                                         Volcan fuenteDeRecursos) {
        this.propetario = propetario;
        this.mapa = mapa;
        this.lugar = lugar;
        this.fuenteDeRecursos = fuenteDeRecursos;
    }

    @Override
    public EstrategiaFicha pasarTurno() {
        int gasExtraido = fuenteDeRecursos.extraer(CANTIDAD_EXTRAIDA_POR_TURNO);
        propetario.agregarRecursosLineales(0, gasExtraido);
        return this;
    }


    @Override
    public void morir(Ficha casa) {
        propetario.perderFicha(casa);
        mapa.getCasilla(lugar).eliminarFichaTerrestre();
        mapa.insertar(lugar, fuenteDeRecursos);
    }

}

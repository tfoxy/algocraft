package estrategia.ficha.construccion;

import error.NoSePuedeCrearFicha;
import estrategia.ficha.EstrategiaEdificioRecursosGas;
import estrategia.ficha.EstrategiaFicha;
import ficha.natural.Volcan;
import juego.Recursos;
import juego.Jugador;
import tablero.Casilla;
import tablero.Coordenada;
import tablero.Tablero;

public class EstrategiaConstruccionAsimilador extends EstrategiaConstruccion {

    private Volcan fuenteRecursos;

    public EstrategiaConstruccionAsimilador(Recursos coste, int turnosParaCrear,
            Jugador propetario, Tablero mapa, Coordenada lugar,
            ListaDeTecnologias lasTecnologiasNecesarias) {
        super(
                coste, turnosParaCrear, propetario,
                mapa, lugar, lasTecnologiasNecesarias
        );
    }

    @Override
    public boolean mePuedeCrear() throws NoSePuedeCrearFicha {
        if (!(propetario.tengoSuficientesRecursos(coste))) {
            throw new NoSePuedeCrearFicha("Faltan Recursos");
        }
        // TODO solucionar sin usar instanceof
        if (!(mapa.getCasilla(lugar).getFichaTerrestre() instanceof Volcan)) {
            throw new NoSePuedeCrearFicha("No Es un Volcan");
        }
        if (!(propetario.tienesLasTecnologias(tecnologiasNecesarias))) {
            throw new NoSePuedeCrearFicha("No Tienes las tecnologias");
        }

        return true;
    }

    @Override
    public void creame(Ficha nueva) {
        // TODO Solucionar sin castear
        Casilla casilla = mapa.getCasilla(lugar);
        fuenteRecursos = (Volcan) casilla.getFichaTerrestre();
        casilla.eliminarFichaTerrestre();

        super.creame(nueva);
    }

    @Override
    public EstrategiaFicha pasarTurno() {
        turnosFaltantesParaCrear = turnosFaltantesParaCrear - 1;
        if (turnosFaltantesParaCrear == 0) {
            return new EstrategiaEdificioRecursosGas(
                    propetario, mapa, lugar, fuenteRecursos
            );
        }
        return this;
    }

}

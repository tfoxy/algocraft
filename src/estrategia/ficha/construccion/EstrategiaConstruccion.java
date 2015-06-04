package estrategia.ficha.construccion;

import error.NoSePuedeCrearFicha;
import estrategia.ficha.EstrategiaFicha;

import ficha.Ficha;
import ficha.FichaTerrestre;
import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import tecnologia.ListaDeTecnologias;

public class EstrategiaConstruccion extends EstrategiaFicha {

    protected Recursos coste;
    protected int turnosParaCrear;
    protected TablaJugador propetario;
    protected Tablero mapa;
    protected Coordenada lugar;
    protected ListaDeTecnologias tecnologiasNecesarias;

    public EstrategiaConstruccion(Recursos coste,
                                  int turnosParaCrear,
                                  TablaJugador propetario,
                                  Tablero mapa,
                                  Coordenada lugar,
                                  ListaDeTecnologias tecnologiasNecesarias) {
        this.coste = coste;
        this.turnosParaCrear = turnosParaCrear;
        this.propetario = propetario;
        this.mapa = mapa;
        this.lugar = lugar;
        this.tecnologiasNecesarias = tecnologiasNecesarias;
    }

    @Override
    public boolean mePuedeCrear() throws NoSePuedeCrearFicha {
        if (!(propetario.tengoSuficientesRecursos(coste))) {
            throw new NoSePuedeCrearFicha("Faltan Recursos");
        }
        if (!(mapa.hayEspacioTerreste(lugar))) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        if (!(propetario.tienesLasTecnologias(tecnologiasNecesarias))) {
            throw new NoSePuedeCrearFicha("No Tienes las tecnologias");
        }

        return true;
    }

    @Override
    public void creame(Ficha nueva) {
        creame((FichaTerrestre) nueva);
    }

    public void creame(FichaTerrestre nueva) {
        propetario.gastaRecursos(coste);
        propetario.newFicha(nueva);
        mapa.insertar(lugar, nueva);
    }

    //voy a tener que ahcer hjas solo para variar esta funcion. T.T
    @Override
    public EstrategiaFicha pasarTurno() {
        return this;
    }
}

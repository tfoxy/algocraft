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

    public EstrategiaConstruccion(Ficha ficha) {
        super(ficha);
    }


    @Override
    public EstrategiaFicha pasarTurno() {
        return this;
    }

}

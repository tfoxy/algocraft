package ficha;

import estrategia.ficha.EstrategiaEdificioRecursosGas;
import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.construccion.EstrategiaConstruccionAsimilador;
import ficha.natural.Volcan;
import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import tecnologia.ListaDeTecnologias;
import tecnologia.Tecnologia;


public class Asimilador extends FichaAbstracta implements FichaTerrestre {

    private Recursos coste;
    private int turnosParaCrear;
    private EstrategiaFicha estrategia;
    private ListaDeTecnologias tecnologiasNecesarias;

    public Asimilador(TablaJugador propetario) {
        super(propetario);
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        estrategia = new EstrategiaEdificioRecursosGas(
                getPropietario(), null, null, new Volcan()
        );
    }

    public Asimilador(TablaJugador propetario, Coordenada lugar, Tablero mapa) {
        super(propetario);
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        estrategia = new EstrategiaConstruccionAsimilador(
                coste, turnosParaCrear, getPropietario(),
                mapa, lugar, tecnologiasNecesarias
        );

        if (estrategia.mePuedeCrear()) {
            estrategia.creame(this);
        }
    }

    @Override
    public void pasarTurno() {
        estrategia = estrategia.pasarTurno();
    }

    @Override
    public void muerete() {
        estrategia.morir(this);

    }

}

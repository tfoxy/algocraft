package ficha;

import estrategia.ficha.EstrategiaCasa;
import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.construccion.EstrategiaConstruccionPilon;
import jugador.Recursos;
import jugador.TablaJugador;

import tablero.Coordenada;
import tablero.Tablero;
import tecnologia.ListaDeTecnologias;
import tecnologia.Tecnologia;

public class Pilon extends Edificio implements FichaTerrestre {

    private Recursos coste;
    private int turnosParaCrear;
    private EstrategiaFicha estrategia;
    private ListaDeTecnologias tecnologiasNecesarias;


    public Pilon(TablaJugador propetario) {
        super(propetario);
        coste = new Recursos(100, 0);
        turnosParaCrear = 5;
        estrategia = new EstrategiaCasa(propetario, null, null);
    }

    public Pilon(TablaJugador propetario, Coordenada lugar, Tablero mapa) {
        super(propetario);
        coste = new Recursos(100, 0, 0);
        turnosParaCrear = 5;
        tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        estrategia = new EstrategiaConstruccionPilon(
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

package ficha;


import error.NoSePuedeCrearFicha;
import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.construccion.EstrategiaConstruccionUnidadSoldado;
import jugador.Recursos;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import tecnologia.ListaDeTecnologias;
import tecnologia.Tecnologia;

public class Zealot extends Unidad implements FichaTerrestre {

    private Recursos coste;
    private int turnosParaCrear;
    private EstrategiaFicha estrategia;
    private ListaDeTecnologias tecnologiasNecesarias;

    public Zealot(TablaJugador propetario, Coordenada lugar, Tablero mapa) {
        super(propetario);
        coste = new Recursos(100, 0, 2);
        turnosParaCrear = 4;
        tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasNecesarias.agregar(Tecnologia.ACCESO);
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        estrategia = new EstrategiaConstruccionUnidadSoldado(
                coste, turnosParaCrear, getPropietario(),
                mapa, lugar, tecnologiasNecesarias
        );

        if (estrategia.mePuedeCrear()) {
            estrategia.creame(this);
        }
    }

    public Zealot(TablaJugador propetario) throws NoSePuedeCrearFicha {
        super(propetario);
        coste = new Recursos(100, 0, 2);
        turnosParaCrear = 4;
        if (hayRecursosSuficientesParaCrearme(coste)) {
            propetario.gastaRecursos(coste);
            propetario.newFicha(this);
        } else {
            throw new NoSePuedeCrearFicha("Faltan Recursos");
        }

    }

    @Override
    public void pasarTurno() {
    }

    @Override
    public void muerete() {
        estrategia.morir(this);
    }

}

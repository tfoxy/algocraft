package ficha;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import jugador.Tecnologia;


public class RecursosTest {

    private TablaJugador protoss;

    @Before
    public void initialize() {
        protoss = new TablaJugador("Proto", Tecnologia.PROTOSS, 200, 200);
    }

    @Test
    public void recuersosPorTurnoCristaBasico() {
        Ficha nuevoEdificio = new NexoMineral(protoss);

        protoss.newFicha(nuevoEdificio);

        protoss.pasarTurno();

        assertEquals(210, protoss.cantidadCristal());
    }

    @Test
    public void recuersosPorTurnoCristal() {
        Ficha nuevoEdificio = new NexoMineral(protoss);

        protoss.newFicha(nuevoEdificio);
        protoss.newFicha(nuevoEdificio);

        protoss.pasarTurno();
        protoss.pasarTurno();

        assertEquals(240, protoss.cantidadCristal());

    }

    @Test
    public void recuersosPorTurnoGasBasicol() {
        Ficha nuevoEdificio = new Asimilador(protoss);
        protoss.newFicha(nuevoEdificio);

        protoss.pasarTurno();

        assertEquals(210, protoss.cantidadGas());
    }

    @Test
    public void recuersosPorTurnoGasYCristalBasico() {
        Ficha nuevoEdificio = new Asimilador(protoss);
        protoss.newFicha(nuevoEdificio);
        nuevoEdificio = new NexoMineral(protoss);
        protoss.newFicha(nuevoEdificio);
        protoss.pasarTurno();
        assertEquals(210, protoss.cantidadGas());
        assertEquals(210, protoss.cantidadCristal());
    }

    @Test
    public void recuersosPorTurnoGasYCristalComplejo() {
        Ficha nuevoEdificio = new Asimilador(protoss);
        protoss.newFicha(nuevoEdificio);
        nuevoEdificio = new NexoMineral(protoss);
        protoss.newFicha(nuevoEdificio);
        protoss.newFicha(nuevoEdificio);
        protoss.pasarTurno();
        protoss.pasarTurno();
        assertEquals(220, protoss.cantidadGas());
        assertEquals(240, protoss.cantidadCristal());
    }

    @Test
    public void perderFuenteDeRecursosYVerficiarQueNoLosGanesPorTurno() {
        Ficha nuevoEdificio = new Asimilador(protoss);
        protoss.newFicha(nuevoEdificio);
        nuevoEdificio = new NexoMineral(protoss);
        protoss.newFicha(nuevoEdificio);
        protoss.newFicha(nuevoEdificio);
        protoss.pasarTurno();
        protoss.pasarTurno();
        assertEquals(220, protoss.cantidadGas());
        assertEquals(240, protoss.cantidadCristal());
        protoss.perderFicha(nuevoEdificio);
        protoss.pasarTurno();
        protoss.pasarTurno();
        assertEquals(240, protoss.cantidadGas());
        assertEquals(260, protoss.cantidadCristal());
    }


    @Test
    public void creamosUnidadPerdemosPoblacion() {
        Ficha nuevoEdificio = new Pilon(protoss);

        protoss.agregarTecnologia(Tecnologia.ACCESO);
        protoss.newFicha(nuevoEdificio);

        new Zealot(protoss);

        assertEquals(2, protoss.poblcacionActual());
    }


    @Test
    public void muereUnidadGanamosPoblacion() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(5, 5);
        Ficha nuevoEdificio = new Pilon(protoss);

        protoss.agregarTecnologia(Tecnologia.ACCESO);
        protoss.newFicha(nuevoEdificio);

        Ficha nuevaUnidad = new Zealot(protoss, lugar, mapa);
        nuevaUnidad.muerete();

        assertEquals(0, protoss.poblcacionActual());
    }

    @Test
    public void poblacionPosibleEsCeroAlInicio() {
        assertEquals(0, protoss.poblcacionPosible());
    }

    @Test
    public void hayPoblacionPosibleAlCrearCasa() {
        Ficha ficha = new Pilon(protoss);

        assertEquals(5, protoss.poblcacionPosible());
    }

    @Test
    public void muereCasaPerdemosPoblacion() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(5, 5);
        Ficha ficha = new Pilon(protoss, lugar, mapa);

        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();

        assertEquals(5, protoss.poblcacionPosible());

        ficha.muerete();

        assertEquals(0, protoss.poblcacionPosible());

    }
}

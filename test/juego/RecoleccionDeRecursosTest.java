package juego;

import static org.junit.Assert.assertEquals;

import ficha.*;
import ficha.protoss.edificios.Asimilador;
import ficha.protoss.edificios.NexoMineral;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;


public class RecoleccionDeRecursosTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 200, 200);
    }

    @Test
    public void aumentarMineralEnUnTurno() {
        Ficha nuevoEdificio = new NexoMineral();

        protoss.asignar(nuevoEdificio);

        protoss.pasarTurno();

        assertEquals(210, protoss.recursos().mineral());
    }

    @Test
    public void aumentarDobleDeMineralAlTenerDosRecolectores() {
        protoss.asignar(new NexoMineral());
        protoss.asignar(new NexoMineral());

        protoss.pasarTurno();

        assertEquals(220, protoss.recursos().mineral());
    }

    @Test
    public void perderFuenteDeRecursosYVerficiarQueNoLosGanesPorTurno() {
        Ficha nuevoEdificio = new Asimilador();

        protoss.asignar(nuevoEdificio);

        protoss.perderFicha(nuevoEdificio);
        assertEquals(240, protoss.cantidadGas());
        assertEquals(260, protoss.cantidadMineral());
    }
}

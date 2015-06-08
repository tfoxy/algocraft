package ficha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import error.NoSePuedeCrearFicha;
import ficha.natural.Volcan;
import jugador.TablaJugador;
import tablero.Coordenada;
import tablero.Tablero;
import jugador.Tecnologia;


public class CrearUnidadesTest {

    private TablaJugador protoss;

    @Before
    public void initialize() {
        protoss = new TablaJugador("Proto", Tecnologia.PROTOSS, 500, 200);
    }

    @Test
    public void usarRecursosCorrectos() {
        Ficha nuevoEdificio = new Pilon(protoss);
        new Zealot(protoss);
        protoss.newFicha(nuevoEdificio);

        assertEquals(protoss.poblcacionActual(), 2);
        assertEquals(protoss.cantidadGas(), 200);
        assertEquals(protoss.cantidadCristal(), 400);
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void noPuedeCrearSiNohayRecurzos() {
        new Zealot(protoss);
    }


    @Test
    public void crearCasaYQueDePoblacion() {
        //esto cocnierne a los dos grupos de Text.//
        Ficha nuevoEdificio = new Pilon(protoss);

        protoss.newFicha(nuevoEdificio);

        assertEquals(protoss.poblcacionPosible(), 5);
    }


    @Test
    public void queTardeElTiempoCorrecto() throws NoSePuedeCrearFicha {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);
        Ficha ficha = new Pilon(protoss, lugar, mapa);

        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();

        assertEquals(protoss.poblcacionPosible(), 5);
    }


    @Test
    public void queNoTardeMenosQueTiempoCorrecto() throws NoSePuedeCrearFicha {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);
        Ficha ficha = new Pilon(protoss, lugar, mapa);

        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();

        assertNotEquals(protoss.poblcacionPosible(), 5);
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void faltanTecnologias() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);
        TablaJugador humanos =
                new TablaJugador("humanos", Tecnologia.TERRAN, 500, 200);

        new Pilon(humanos, lugar, mapa);
    }


    @Test
    public void construccionDeAsimiladorEnUnVolcan() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);
        new Volcan(2000, lugar, mapa);
        Ficha ficha = new Asimilador(protoss, lugar, mapa);

        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        ficha.pasarTurno();
        // ElTurno que se crea tambien trabaja.

        assertNotEquals(210, protoss.cantidadGas());
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void construccionDeAsimiladorSinUnVolcan() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);

        new Asimilador(protoss, lugar, mapa);
    }

}

package modulos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import juego.Jugador;
import juego.Raza;

import org.junit.Before;
import org.junit.Test;

import error.NoSePuedeCrearFicha;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import ficha.CasaTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.natural.Volcan;
import ficha.protoss.Asimilador;
import ficha.protoss.Pilon;

import tablero.Coordenada;
import tablero.Tablero;

public class ModuloConstruccionTest {

    private Jugador protoss;
    private Tablero mapa;
    private ModuloConstruccion modulo = new ModuloConstruccion();

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        mapa = new Tablero(20,20);
    }
    
    @Test
    public void usarRecursosCorrectos() {
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void falloPorFaltaDeRecursos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);
    }
    
    /*
         @Test(expected = NoSePuedeCrearFicha.class)
    public void noPuedeCrearSiNohayRecurzos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        EdificioTerrestre nuevoEdificio = new Pilon();
        protoss.construir(nuevoEdificio);
    }


    @Test
    public void queNoTardeMenosQueTiempoCorrecto() throws NoSePuedeCrearFicha {
        Pilon pilon = new Pilon();
        protoss.construir(pilon);

        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();

        assertNotEquals(5, protoss.poblacionPosible());
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void faltanTecnologias() {
        Jugador humanos = new Jugador("humanos", Raza.TERRAN);
        Pilon pilon = new Pilon();

        humanos.asignar(pilon);
    }


    @Test
    public void construccionDeAsimiladorEnUnVolcan() {
        Ficha ficha = new Asimilador();
        ficha.fuenteDeRecursos(new Volcan(2000));



        assertEquals(200, protoss.cantidadGas());
    }


    @Test(expected = NoSePuedeCrearFicha.class)
    public void construccionDeAsimiladorSinUnVolcan() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(3, 3);

        Asimilador asimilador = new Asimilador();
        mapa.insertar(lugar, asimilador);
    }

      */
	
}

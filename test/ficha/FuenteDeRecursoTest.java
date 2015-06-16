package ficha;

import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import juego.Tecnologia;
import ficha.natural.recurso.Volcan;
import ficha.protoss.edificio.Asimilador;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;

import static org.junit.Assert.assertEquals;

public class FuenteDeRecursoTest {

    private Jugador protoss;
    private Jugador pachaMama; //vende patria
    private Tablero mapa;
    private Ficha nuevoEdificio;
    private Coordenada coordenada;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20, 20);
        coordenada = new Coordenada(1, 1);
        protoss.agregarTecnologia(Tecnologia.ACCESO);
        nuevoEdificio = new Asimilador();

    }


    @Test
    public void extracionNormal() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        protoss.pasarTurno();

        assertEquals(4990, nuevoRecurso.cantidadDeGas());
        assertEquals(210, protoss.cantidadGas());
    }

    @Test
    public void noExtraeRecursosSiNoTiene() {
        FuenteDeRecurso nuevoRecurso = new Volcan(0);
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        protoss.pasarTurno();

        assertEquals(200, protoss.cantidadGas());
    }

    @Test
    public void extraeLoQueTieneSiSeQuiereExtraerDeMas() {
        FuenteDeRecurso nuevoRecurso = new Volcan(1);
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        protoss.pasarTurno();

        assertEquals(201, protoss.cantidadGas());
    }

    @Test
    public void noExtraSiNoEstaConsturido() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        nuevoEdificio.enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        protoss.pasarTurno();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(5000, nuevoRecurso.cantidadDeGas());
    }

}

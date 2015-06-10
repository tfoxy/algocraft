package ficha;

import juego.Jugador;
import juego.Raza;
import juego.RecursosDeJugador.Poblacion;
import juego.Tecnologia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ficha.protoss.Pilon;
import ficha.protoss.Zealot;
import tablero.Coordenada;
import tablero.Tablero;

import static org.junit.Assert.assertEquals;

public class CasaTest {

    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS);
    }

    @Test
    public void crearCasaYQueDePoblacion() {
        Ficha ficha = new Pilon();
        Poblacion poblacion = protoss.recursos().poblacion();

        protoss.asignar(ficha);

        Assert.assertEquals(5, poblacion.posible());
    }


    @Test
    public void muereCasaPerdemosPoblacion() {
        Ficha ficha = new Pilon();
        Poblacion poblacion = protoss.recursos().poblacion();

        protoss.asignar(ficha);

        protoss.pasarTurno();

        ficha.muerete();

        Assert.assertEquals(0, poblacion.posible());
    }


    @Test
    public void creamosUnidadPerdemosPoblacion() {
        Ficha nuevoEdificio = new Pilon();

        protoss.agregarTecnologia(Tecnologia.ACCESO);
        protoss.newFicha(nuevoEdificio);

        protoss.newFicha(new Zealot());

        assertEquals(2, protoss.poblcacionActual());
    }


    @Test
    public void muereUnidadGanamosPoblacion() {
        Tablero mapa = new Tablero(10, 10);
        Coordenada lugar = new Coordenada(5, 5);
        Ficha nuevoEdificio = new Pilon();

        protoss.newFicha(nuevoEdificio);
        protoss.agregarTecnologia(Tecnologia.ACCESO);

        Ficha nuevaUnidad = new Zealot();
        protoss.newFicha(nuevaUnidad);
        nuevaUnidad.muerete();

        assertEquals(0, protoss.poblcacionActual());
    }

}

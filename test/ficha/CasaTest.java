package ficha;

import juego.Jugador;
import juego.Raza;
import juego.RecursosDeJugador.Poblacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ficha.protoss.edificio.Pilon;
import ficha.protoss.unidad.Zealot;
import tablero.Coordenada;
import tablero.Tablero;

import static org.junit.Assert.assertEquals;

public class CasaTest {

    private Jugador protoss;
    private Tablero mapa;
    private Coordenada coordenada;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS);
        mapa = new Tablero(3, 3);
        coordenada = new Coordenada(1, 1);
    }

    @Test
    public void crearCasaYQueDePoblacion() {
        Poblacion poblacion = protoss.recursos().poblacion();

        Ficha ficha = new Pilon();
        ficha.setBasico(protoss, mapa, coordenada);
        ficha.ponerEnJuego();

        Assert.assertEquals(5, poblacion.posible());
    }


    @Test
    public void muereCasaPerdemosPoblacion() {
        Poblacion poblacion = protoss.recursos().poblacion();

        Ficha ficha = new Pilon();
        ficha.setBasico(protoss, mapa, coordenada);
        ficha.ponerEnJuego();

        protoss.pasarTurno();

        ficha.muerete();

        Assert.assertEquals(0, poblacion.posible());
    }


    @Test
    public void creamosUnidadPerdemosPoblacion() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        Ficha nuevaUnidad = new Zealot();
        nuevaUnidad.setBasico(protoss, mapa, new Coordenada(2, 2));
        nuevaUnidad.ponerEnJuego();

        assertEquals(2, protoss.poblcacionActual());
    }


    @Test
    public void muereUnidadGanamosPoblacion() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        Ficha nuevaUnidad = new Zealot();
        nuevaUnidad.setBasico(protoss, mapa, new Coordenada(2, 2));
        nuevaUnidad.ponerEnJuego();

        nuevaUnidad.muerete();

        assertEquals(0, protoss.poblcacionActual());
    }

}

package ficha;

import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;

import ficha.protoss.edificio.Pilon;
import tablero.Coordenada;
import tablero.ITablero;
import tablero.Tablero;

import static org.junit.Assert.assertEquals;

public class ConstruccionTest {

    private Jugador protoss;
    private ITablero mapa;
    private Coordenada coordenada;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        mapa = new Tablero(20, 20);
        coordenada = new Coordenada(10, 10);
        protoss.visibilidad().verDesde(coordenada, 2);
    }

    @Test
    public void queTardeElTiempoCorrecto() {
        Ficha pilon = new Pilon().enConstruccion();
        pilon.setBasico(protoss, mapa, coordenada);
        pilon.ponerEnJuego();

        for (int i = 0; i < pilon.turnosParaCrear(); i++) {
            assertEquals(protoss.poblacionPosible(), 0);
            pilon.pasarTurno();
        }

        assertEquals(protoss.poblacionPosible(), pilon.poblacionQueDa);
    }

    // TODO hacer tests

}

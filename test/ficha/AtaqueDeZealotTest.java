package ficha;

import estrategia.ficha.EstrategiaAtacarYSerAtacado;
import jugador.TablaJugador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Tablero;
import jugador.Tecnologia;

public class AtaqueDeZealotTest {

    private EstrategiaAtacarYSerAtacado estrategia;
    private Tablero mapa;
    private FichaTerrestre unidad;
    private FichaTerrestre unidadEnemigaTerrestre;
    private FichaAerea unidadEnemigaAerea;
    private TablaJugador jugador;
    private TablaJugador enemigo;


    @Before
    public void initialize() {
        estrategia = new EstrategiaAtacarYSerAtacado();

        int tamanioDeMapa = 40;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);

        jugador = new TablaJugador("miNombre", Tecnologia.PROTOSS);
        enemigo = new TablaJugador("miEnemigo", Tecnologia.TERRAN);

        unidad = new Zealot(jugador);

        unidadEnemigaTerrestre = new Marine(jugador);
        unidadEnemigaAerea = new Espectro(jugador);
    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), unidadEnemigaTerrestre);

        boolean fueAtacado =
                estrategia.atacado(unidad, unidadEnemigaTerrestre);

        Assert.assertTrue(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadAereaAdyacente() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), unidadEnemigaAerea);

        boolean fueAtacado =
                estrategia.atacado(unidad, unidadEnemigaAerea);

        Assert.assertFalse(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 3), unidadEnemigaAerea);

        boolean fueAtacado =
                estrategia.atacado(unidad, unidadEnemigaAerea);

        Assert.assertFalse(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadTerrestreLejana() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 5), unidadEnemigaTerrestre);

        boolean fueAtacado =
                estrategia.atacado(unidad, unidadEnemigaTerrestre);

        Assert.assertFalse(fueAtacado);
    }


    // TODO hacer mas tests

}

package ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloAtacarYSerAtacado;
import ficha.protoss.Zealot;
import ficha.terrans.Espectro;
import ficha.terrans.Marine;
import juego.Jugador;
import juego.Raza;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Tablero;

public class AtaqueDeZealotTest {

    private ModuloAtacarYSerAtacado estrategia;
    private Tablero mapa;
    private FichaTerrestre unidad;
    private FichaTerrestre unidadEnemigaTerrestre;
    private FichaAerea unidadEnemigaAerea;
    private Jugador jugador;
    private Jugador enemigo;


    @Before
    public void initialize() {
        estrategia = new ModuloAtacarYSerAtacado();

        int tamanioDeMapa = 40;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);

        jugador = new Jugador("miNombre", Raza.PROTOSS);
        enemigo = new Jugador("miEnemigo", Raza.TERRAN);

        unidad = new Zealot();
        jugador.asignar(unidad);

        unidadEnemigaTerrestre = new Marine();
        unidadEnemigaAerea = new Espectro();

        enemigo.asignar(unidadEnemigaTerrestre);
        enemigo.asignar(unidadEnemigaAerea);
    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), unidadEnemigaTerrestre);

        boolean fueAtacado =
                estrategia.atacar(unidad, unidadEnemigaTerrestre);

        Assert.assertTrue(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadAereaAdyacente() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 4), unidadEnemigaAerea);

        boolean fueAtacado =
                estrategia.atacar(unidad, unidadEnemigaAerea);

        Assert.assertFalse(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 3), unidadEnemigaAerea);

        boolean fueAtacado =
                estrategia.atacar(unidad, unidadEnemigaAerea);

        Assert.assertFalse(fueAtacado);
    }


    @Test
    public void noPuedeAtacarUnidadTerrestreLejana() {
        mapa.insertar(new Coordenada(3, 3), unidad);
        mapa.insertar(new Coordenada(3, 5), unidadEnemigaTerrestre);

        boolean fueAtacado =
                estrategia.atacar(unidad, unidadEnemigaTerrestre);

        Assert.assertFalse(fueAtacado);
    }


    // TODO hacer mas tests

}

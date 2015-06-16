package estrategias;

import error.FueraDeRangoException;
import juego.Jugador;
import juego.Raza;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.protoss.unidad.Zealot;
import ficha.terran.unidad.Espectro;
import ficha.terran.unidad.Marine;

public class ExtrategiaVivaAtaquesTest {

    private Tablero mapa;
    private FichaTerrestre unidad;
    private FichaTerrestre unidadEnemigaTerrestre;
    private FichaAerea unidadEnemigaAerea;
    private Jugador jugador;
    private Jugador enemigo;


    @Before
    public void initialize() {
        int tamanioDeMapa = 40;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);

        jugador = new Jugador("miNombre", Raza.PROTOSS);
        enemigo = new Jugador("miEnemigo", Raza.TERRAN);

        unidad = new Zealot();
        unidad.setBasico(jugador, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();

        unidadEnemigaTerrestre = new Marine();
        unidadEnemigaAerea = new Espectro();

    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
        unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3, 4));
        unidadEnemigaTerrestre.ponerEnJuego();

        unidad.atacar(unidadEnemigaTerrestre);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaAdyacente() {
        unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3, 4));
        unidadEnemigaAerea.ponerEnJuego();

        unidad.atacar(unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
        unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3, 3));
        unidadEnemigaAerea.ponerEnJuego();

        unidad.atacar(unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadTerrestreLejana() {
        unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3, 5));
        unidadEnemigaTerrestre.ponerEnJuego();

        unidad.atacar(unidadEnemigaTerrestre);
    }


}

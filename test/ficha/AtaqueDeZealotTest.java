package ficha;

import estrategia.ficha.ExtrategiaConstrucccionOP;
import error.FueraDeRangoException;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccionOP;
import ficha.protoss.unidades.Zealot;
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
/*
 * Todas estas pruevan estan en la version nuva en Extrategia Viva Ataque Test.
 * 
    private ExtrategiaConstrucccionOP moduloAux;
    private Tablero mapa;
    private FichaTerrestre unidad;
    private FichaTerrestre unidadEnemigaTerrestre;
    private FichaAerea unidadEnemigaAerea;
    private Jugador jugador;
    private Jugador enemigo;


    @Before
    public void initialize() {
        moduloAux = new ExtrategiaConstrucccionOP();
        
        int tamanioDeMapa = 40;

        mapa = new Tablero(tamanioDeMapa, tamanioDeMapa);

        jugador = new Jugador("miNombre", Raza.PROTOSS);
        enemigo = new Jugador("miEnemigo", Raza.TERRAN);

        unidad = new Zealot();
        unidad.setBasico(jugador, mapa, new Coordenada (3,3));
        moduloAux.PonerEnJuego(unidad);

        unidadEnemigaTerrestre = new Marine();
        unidadEnemigaAerea = new Espectro();

    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.PonerEnJuego(unidadEnemigaTerrestre);

        estrategia.realizarAtaque(unidad, unidadEnemigaTerrestre);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaAdyacente() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.PonerEnJuego(unidadEnemigaAerea);

        estrategia.realizarAtaque(unidad, unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,3));
        moduloAux.PonerEnJuego(unidadEnemigaAerea);

        estrategia.realizarAtaque(unidad, unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadTerrestreLejana() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,5));
        moduloAux.PonerEnJuego(unidadEnemigaTerrestre);

        estrategia.realizarAtaque(unidad, unidadEnemigaTerrestre);
    }


    // TODO hacer mas tests
*/
}

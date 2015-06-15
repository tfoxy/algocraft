package estrategias;

import juego.Jugador;
import juego.Raza;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.protoss.unidades.Zealot;
import ficha.terrans.Espectro;
import ficha.terrans.Marine;

public class ExtrategiaVivaAtaquesTest {

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
        moduloAux.ponerEnJuego(unidad);

        unidadEnemigaTerrestre = new Marine();
        unidadEnemigaAerea = new Espectro();

    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.ponerEnJuego(unidadEnemigaTerrestre);

        unidad.atacar(unidadEnemigaTerrestre);
    }


    @Test
    public void noPuedeAtacarUnidadAereaAdyacente() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.ponerEnJuego(unidadEnemigaAerea);

        Assert.assertFalse(unidad.atacar(unidadEnemigaAerea));
    }


    @Test
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,3));
        moduloAux.ponerEnJuego(unidadEnemigaAerea);

        Assert.assertFalse(unidad.atacar(unidadEnemigaAerea));
    }


    @Test
    public void noPuedeAtacarUnidadTerrestreLejana() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,5));
        moduloAux.ponerEnJuego(unidadEnemigaTerrestre);

        Assert.assertFalse(unidad.atacar(unidadEnemigaTerrestre));
    }


	
}

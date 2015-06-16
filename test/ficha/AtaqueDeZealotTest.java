package ficha;

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
        moduloAux.ponerEnJuego(unidad);

        unidadEnemigaTerrestre = new Marine();
        unidadEnemigaAerea = new Espectro();

    }


    @Test
    public void puedeAtacarUnidadTerrestreAdyacente() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.ponerEnJuego(unidadEnemigaTerrestre);

        estrategia.atacar(unidad, unidadEnemigaTerrestre);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaAdyacente() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,4));
        moduloAux.ponerEnJuego(unidadEnemigaAerea);

        estrategia.atacar(unidad, unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadAereaEnLaMismaPosicion() {
    	unidadEnemigaAerea.setBasico(enemigo, mapa, new Coordenada(3,3));
        moduloAux.ponerEnJuego(unidadEnemigaAerea);

        estrategia.atacar(unidad, unidadEnemigaAerea);
    }


    @Test(expected = FueraDeRangoException.class)
    public void noPuedeAtacarUnidadTerrestreLejana() {
    	unidadEnemigaTerrestre.setBasico(enemigo, mapa, new Coordenada(3,5));
        moduloAux.ponerEnJuego(unidadEnemigaTerrestre);

        estrategia.atacar(unidad, unidadEnemigaTerrestre);
    }


    // TODO hacer mas tests
*/
}

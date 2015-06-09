package modulos;

import static org.junit.Assert.assertEquals;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import estrategia.ficha.moduloDeEstrategias.ModuloMorir;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.FuenteDeRecurso;
import ficha.UnidadTerrestre;
import ficha.natural.Volcan;
import ficha.protoss.Asimilador;
import ficha.protoss.Pilon;
import ficha.protoss.Zealot;

public class ModuloMorirTest {

    private Jugador protoss;
    private Jugador pachaMama;//vende patria
    private Tablero mapa;
    private ModuloConstruccion moduloAux = new ModuloConstruccion();
    private ModuloMorir modulo = new ModuloMorir();

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20,20);
    }
    

    @Test
    public void MuereCasaPierdesPoblacionTotal() {
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        moduloAux.PonerEnJuego(nuevoEdificio);
        protoss.agregarPoblacionTotal(10); //el agregar poblacion iria durante la Extrategia. No en el moduloConstucccion.

        modulo.morir(nuevoEdificio);
        
        assertEquals(5, protoss.poblacionPosible());
    }
    
    @Test
    public void MuereTerrestreSeLiveraElEspacio() {
    	EdificioTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        moduloAux.PonerEnJuego(nuevoEdificio);

        modulo.morir(nuevoEdificio);
        
        Assert.assertTrue(mapa.hayEspacioTerreste(new Coordenada(1,1)));
    }
    
    
    @Test
    public void MuereUnidadPierdesPoblacionaActual() {
        UnidadTerrestre nuevaUnidad = new Zealot();
        protoss.agregarPoblacionTotal(10);
        nuevaUnidad.setBasico(protoss, mapa, new Coordenada(1,1));
        moduloAux.PonerEnJuego(nuevaUnidad);

        modulo.morir(nuevaUnidad); //el morir funciona
        
        assertEquals(400, protoss.cantidadMineral());
        assertEquals(0, protoss.poblcacionActual());
    }
    
    @Test
    public void MuereFuenteDeRecursoDejaElRecurso() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        Coordenada coordenada = new Coordenada (1,1);
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        moduloAux.PonerEnJuego(nuevoRecurso);
    	
    	EdifcioDeRecusosTerrestre nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, coordenada);
        moduloAux.PonerEnJuego(nuevoEdificio);

        modulo.morir(nuevoEdificio);
        assertEquals(pachaMama, mapa.getCasilla(coordenada).getFichaTerrestre().propietario());
    }

}

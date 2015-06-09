package Extrategias;

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
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.FuenteDeRecurso;
import ficha.UnidadTerrestre;
import ficha.natural.Volcan;
import ficha.protoss.Asimilador;
import ficha.protoss.Pilon;
import ficha.protoss.Zealot;

public class ExtratregiaContruccionTest {

    private Jugador protoss;
    private Jugador pachaMama;//vende patria
    private Tablero mapa;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20,20);
    }
    
    //fichaTerrestre/casa
    @Test
    public void usarRecursosCorrectos() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevoEdificio.PonerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void falloPorFaltaDeRecursos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevoEdificio.PonerEnJuego();
    }
    
       
    @Test (expected = NoSePuedeCrearFicha.class)
    public void falloPorSobreposicion() {
        Ficha nuevoEdificio = new Pilon();
        Coordenada coordenada = new Coordenada(1,1);
        
        
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.PonerEnJuego();
        
        nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.PonerEnJuego();
    }
    //fichaTerrestre/casa
    

    //recursoTerrestre

    @Test
    public void crearsseEnLugarCorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        nuevoRecurso.PonerEnJuego();

        Assert.assertFalse(mapa.hayEspacioTerreste(new Coordenada(1,1)));
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        nuevoRecurso.PonerEnJuego();

        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        nuevoRecurso.PonerEnJuego();
    }

    //recursoTerrestre
    
    // EdifcioDeRecusosTerrestre
    
    @Test
    public void crearsseEnLugarCorrectoEdifcioDeRecusosTerrestre() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        nuevoRecurso.PonerEnJuego();
    	
    	Ficha nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
    	nuevoEdificio.PonerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoEdifcioDeRecusosTerrestre() {
    	Ficha nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
    	nuevoEdificio.PonerEnJuego();
        
    }
    
    // EdifcioDeRecusosTerrestre

    //turnos
    @Test
    public void tiempoCorrecto() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevoEdificio.PonerEnJuego();
        
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        
        assertEquals(5, protoss.poblacionPosible());
        
    }

    
    //esto hirian en las pruevas de Extrategia Ficha.. pero al ser una clase abstarcta no se puede hacer.
    
    @Test
    public void MuereCasaEnConsturccionNoPasaNadal() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevoEdificio.PonerEnJuego();
        protoss.agregarPoblacionTotal(10); //para que se agrege la poblacion tiene que pasar los turnos de la consturccion

        nuevoEdificio.muerete();
        
        assertEquals(10, protoss.poblacionPosible());
    }
    
    @Test
    public void MuereTerrestreSeLiveraElEspacio() {
    	Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevoEdificio.PonerEnJuego();

        nuevoEdificio.muerete();
        
        Assert.assertTrue(mapa.hayEspacioTerreste(new Coordenada(1,1)));
    }
    
    
    @Test
    public void MuereUnidadPierdesPoblacionaActual() {
        Ficha nuevaUnidad = new Zealot();
        protoss.agregarPoblacionTotal(10);
        nuevaUnidad.setBasico(protoss, mapa, new Coordenada(1,1));
        nuevaUnidad.PonerEnJuego();

        nuevaUnidad.muerete();
        
        assertEquals(400, protoss.cantidadMineral());
        assertEquals(0, protoss.poblcacionActual());
    }
    
    @Test
    public void MuereFuenteDeRecursoDejaElRecurso() {
        Ficha nuevoRecurso = new Volcan();
        Coordenada coordenada = new Coordenada (1,1);
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.PonerEnJuego();
    	
    	Ficha nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, coordenada);
    	nuevoEdificio.PonerEnJuego();

        nuevoEdificio.muerete();
        assertEquals(pachaMama, mapa.getCasilla(coordenada).getFichaTerrestre().propietario());
    }
}

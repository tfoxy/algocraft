package modulos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import error.NoSePuedeCrearFicha;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.FuenteDeRecurso;
import ficha.natural.Volcan;
import ficha.protoss.Asimilador;
import ficha.protoss.Pilon;

import tablero.Coordenada;
import tablero.Tablero;

public class ModuloConstruccionTest {

    private Jugador protoss;
    private Jugador pachaMama;//vende patria
    private Tablero mapa;
    private ModuloConstruccion modulo = new ModuloConstruccion();

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20,20);
    }
    
    //fichaTerrestre/casa
    @Test
    public void usarRecursosCorrectos() {
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void falloPorFaltaDeRecursos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void falloPorSobreposicion() {
        CasaTerrestre nuevoEdificio = new Pilon();
        Coordenada coordenada = new Coordenada(1,1);
        
        
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        modulo.PonerEnJuego(nuevoEdificio);
        
        nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        modulo.PonerEnJuego(nuevoEdificio);
    }
    //fichaTerrestre/casa
    

    //recursoTerrestre
    
    @Test
    public void crearsseEnLugarCorrectoFuenteDeRecursos() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoRecurso);

        Assert.assertFalse(mapa.hayEspacioTerreste(new Coordenada(1,1)));
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoFuenteDeRecursos() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoRecurso);

        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoRecurso);
    }

    //recursoTerrestre
    
    // EdifcioDeRecusosTerrestre
    
    @Test
    public void crearsseEnLugarCorrectoEdifcioDeRecusosTerrestre() {
        FuenteDeRecurso nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoRecurso);
    	
    	EdifcioDeRecusosTerrestre nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }
    
    @Test (expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoEdifcioDeRecusosTerrestre() {
    	EdifcioDeRecusosTerrestre nuevoEdificio = new Asimilador();
    	nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);
        
    }
    
    // EdifcioDeRecusosTerrestre

    //turnos
    @Test
    public void tiempoCorrecto() {
        CasaTerrestre nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1,1));
        modulo.PonerEnJuego(nuevoEdificio);
        
        modulo.pasarTurno(nuevoEdificio);
        modulo.pasarTurno(nuevoEdificio);
        modulo.pasarTurno(nuevoEdificio);
        modulo.pasarTurno(nuevoEdificio);
        modulo.pasarTurno(nuevoEdificio);
        
        Assert.assertTrue (modulo.estaCreada(nuevoEdificio));
        
    }
	
}

package ficha;

import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import juego.Tecnologia;
import estrategia.ficha.moduloDeEstrategias.ModuloConstruccionOP;
import ficha.natural.Volcan;
import ficha.protoss.edificios.Asimilador;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

import static org.junit.Assert.assertEquals;

public class FuenteDeRecursoTest {

    private Jugador protoss;
    private Jugador pachaMama; //vende patria
    private Tablero mapa;
    private FuenteDeRecurso nuevoRecurso;
    private Ficha nuevoEdificio;
    private ModuloConstruccionOP moduloAux;
	
    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20, 20);
        protoss.agregarTecnologia(Tecnologia.ACCESO);
        moduloAux = new ModuloConstruccionOP();
        
        }
	
	
    @Test
    public void ExtracionNormal() {
        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego(nuevoRecurso);

        nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego((EdifcioDeRecusosTerrestre)nuevoEdificio);

    	protoss.pasarTurno();
    	
    	 assertEquals(4990, nuevoRecurso.cantidadDeGas());
    	 assertEquals(210, protoss.cantidadGas());
    }

    @Test
    public void noExtraeRecursosSiNoTiene() {
        nuevoRecurso = new Volcan(0);
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego(nuevoRecurso);

        nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego((EdifcioDeRecusosTerrestre)nuevoEdificio);

    	protoss.pasarTurno();
    	
    	 assertEquals(200, protoss.cantidadGas());
    }

    @Test
    public void extraeLoQueTieneSiSeQuiereExtraerDeMas() {
        nuevoRecurso = new Volcan(1);
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego(nuevoRecurso);

        nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego((EdifcioDeRecusosTerrestre)nuevoEdificio);

    	protoss.pasarTurno();
    	
    	 assertEquals(201, protoss.cantidadGas());
    }
    
    @Test
    public void noExtraSiNoEstaConsturido() {
        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, new Coordenada(1, 1));
        moduloAux .ponerEnJuego(nuevoRecurso);

        nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        nuevoEdificio.ponerEnJuego();

    	protoss.pasarTurno();
    	
    	 assertEquals(200, protoss.cantidadGas());
    	 assertEquals(5000, nuevoRecurso.cantidadDeGas());
    }

}

package magia;

import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;
import error.EnergiaInsuficienteException;
import estrategia.ficha.ExtrategiaConstrucccionOP;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import ficha.protoss.Zealot;
import ficha.protoss.unidades.AltoTemplario;
import ficha.protoss.unidades.NaveTransporte;
import ficha.protoss.unidades.Scout;

public class TormentaTest {
	
    private ExtrategiaConstrucccionOP moduloAux;
    private FichaTerrestre Caster;
    private FichaTerrestre victima;
    private FichaAerea victimaVoladora;
    private Tablero mapa;
    private Jugador jugador;
    private Jugador jugadorEnemigo;
    private TormentaPsionicaMagia magia;
    
    @Before
    public void initialize() {
        moduloAux = new ExtrategiaConstrucccionOP();
        magia = new TormentaPsionicaMagia();
        mapa = new Tablero(10, 10);
        Caster = new AltoTemplario();
        victima = new Zealot();
        victimaVoladora = new Scout();
        jugador = new Jugador("aliado", Raza.PROTOSS);
        jugadorEnemigo = new Jugador("enemigo", Raza.PROTOSS);
        
        Caster.setBasico(jugador, mapa, new Coordenada3d(1,1,0));
        victima.setBasico(jugadorEnemigo, mapa, new Coordenada3d(2,2,0));
        victimaVoladora.setBasico(jugadorEnemigo, mapa, new Coordenada3d(2,2,1));
        
        moduloAux.PonerEnJuego(Caster);
        
        jugador.pasarTurno();
        jugador.pasarTurno();
        jugador.pasarTurno();
    }

    @Test
    public void noHacerNadaSiFalla() {
    	magia.realizar(Caster, new Coordenada3d(2,2,0));
    	jugador.pasarTurno(); 	
    }

    
    @Test
    public void dañoDosUnidadesEnUnTurno() {
    	moduloAux.PonerEnJuego(victima);
    	moduloAux.PonerEnJuego(victimaVoladora);
    	magia.realizar(Caster, new Coordenada3d(2,2,0));
    	jugador.pasarTurno();
    	
    	Assert.assertEquals(0, victima.barras().escudoActual());
    	Assert.assertEquals(0, victimaVoladora.barras().escudoActual());  	
    }
    
    
    @Test
    public void dañoDosUnidadesEnDosTurno() {
    	moduloAux.PonerEnJuego(victima);
    	moduloAux.PonerEnJuego(victimaVoladora);
    	magia.realizar(Caster, new Coordenada3d(2,2,0));
    	jugador.pasarTurno();
    	jugador.pasarTurno();
    	
    	Assert.assertTrue(victima.barras().estaMuerto());
    	Assert.assertEquals(50, victimaVoladora.barras().vidaActual());  	
    }
    
    @Test
    public void dañoUnaUnidadesEnUnTurno() {
    	moduloAux.PonerEnJuego(victima);
    	magia.realizar(Caster, new Coordenada3d(2,2,0));
    	jugador.pasarTurno();
    	
    	Assert.assertEquals(0, victima.barras().escudoActual());	
    }
    
    @Test //por alguna razon esta y la siguente si estan las dos a la ves fallan. Pero si se qutia una no fallan. Tira un error de algo de los test.
    public void dañoDosUnidadesEnUnTurnoPeroEsquiva() {
    	moduloAux.PonerEnJuego(victima);
    	magia.realizar(Caster, new Coordenada3d(2,2,0));
    	jugador.pasarTurno();
    	victima.intentarMovimiento(Direccion.ABAJO);
    	jugador.pasarTurno();
    	Assert.assertEquals(0, victima.barras().escudoActual());
    	Assert.assertFalse(victima.barras().estaMuerto());
    }
    
    @Test
    public void dañoDosUnidadesEnUnTurnoPeroFallaElPrimero() {
    	moduloAux.PonerEnJuego(victima);
    	magia.realizar(Caster, new Coordenada3d(2,1,0));
    	jugador.pasarTurno();
    	victima.intentarMovimiento(Direccion.ABAJO);
    	jugador.pasarTurno();
    	Assert.assertEquals(0, victima.barras().escudoActual());	
    }
    
}

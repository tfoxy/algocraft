package estrategias;

import static org.junit.Assert.assertEquals;

import error.FichaSobreOtraFichaException;
import error.RecursosInsuficientesException;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import ficha.Ficha;
import ficha.natural.Volcan;
import ficha.protoss.Asimilador;
import ficha.protoss.Pilon;
import ficha.protoss.Zealot;

public class EstratregiaContruccionTest {

    private Jugador protoss;
    private Jugador pachaMama; //vende patria
    private Tablero mapa;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        pachaMama = new Gaia();
        mapa = new Tablero(20, 20);
    }

    //fichaTerrestre/casa
    @Test
    public void usarRecursosCorrectos() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico2 (protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }

    @Test(expected = RecursosInsuficientesException.class)
    public void falloPorFaltaDeRecursos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico2 (protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void falloPorSobreposicion() {
        Ficha nuevoEdificio = new Pilon();
        Coordenada coordenada = new Coordenada(1, 1);


        nuevoEdificio.setBasico2 (protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();

        nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico2 (protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();
    }
    //fichaTerrestre/casa


    //recursoTerrestre

    @Test
    public void crearseEnLugarCorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico2(pachaMama, mapa, new Coordenada3d(1, 1, 1));
        nuevoRecurso.ponerEnJuego();

        Assert.assertFalse(mapa.hayEspacioTerreste(new Coordenada(1, 1)));
    }

    @Test(expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico2(pachaMama, mapa, new Coordenada3d(1, 1, 1));
        nuevoRecurso.ponerEnJuego();

        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico2(pachaMama, mapa, new Coordenada3d(1, 1, 1));
        nuevoRecurso.ponerEnJuego();
    }

    //recursoTerrestre

    // EdifcioDeRecusosTerrestre

    @Test
    public void crearseEnLugarCorrectoEdifcioDeRecusosTerrestre() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico2(pachaMama, mapa, new Coordenada3d(1, 1, 1));
        nuevoRecurso.ponerEnJuego();

        Ficha nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico2(protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }

    @Test(expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoEdifcioDeRecusosTerrestre() {
        Ficha nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        nuevoEdificio.PonerEnJuego();

    }

    // EdifcioDeRecusosTerrestre

    //turnos
    @Test
    public void tiempoCorrecto() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico2 (protoss, mapa, new Coordenada3d(1, 1, 1));
        nuevoEdificio.ponerEnJuego();
        

        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();
        nuevoEdificio.pasarTurno();

        assertEquals(5, protoss.poblacionPosible());

    }


    //esto hirian en las pruevas de Extrategia Ficha.. pero al ser una clase abstarcta no se puede hacer.

    @Test
    public void muereCasaEnConstruccionNoPasaNada() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        nuevoEdificio.PonerEnJuego();
        protoss.agregarPoblacionTotal(10); //para que se agrege la poblacion tiene que pasar los turnos de la consturccion

        nuevoEdificio.muerete();

        assertEquals(10, protoss.poblacionPosible());
    }

    @Test
    public void muereTerrestreSeLiberaElEspacio() {
        Ficha nuevoEdificio = new Pilon();
        nuevoEdificio.setBasico(protoss, mapa, new Coordenada(1, 1));
        nuevoEdificio.PonerEnJuego();

        nuevoEdificio.muerete();

        Assert.assertTrue(mapa.hayEspacioTerreste(new Coordenada(1, 1)));
    }


    @Test
    public void muereUnidadPierdesPoblacionaActual() {
        Ficha nuevaUnidad = new Zealot();
        protoss.agregarPoblacionTotal(10);
        nuevaUnidad.setBasico(protoss, mapa, new Coordenada (1, 1));
        nuevaUnidad.PonerEnJuego();

        nuevaUnidad.muerete();

        assertEquals(400, protoss.cantidadMineral());
        assertEquals(0, protoss.poblcacionActual());
    }

    @Test
    public void muereFuenteDeRecursoDejaElRecurso() {
        Ficha nuevoRecurso = new Volcan();
        Coordenada coordenada = new Coordenada(1, 1);
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.PonerEnJuego();

        Ficha nuevoEdificio = new Asimilador();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.PonerEnJuego();

        nuevoEdificio.muerete();
        assertEquals(pachaMama, mapa.getFichaTerrestre(coordenada).propietario());
    }

    // TODO test que pruebe TecnologiasInsuficientesException

}

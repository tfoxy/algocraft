package ficha;

import static org.junit.Assert.assertEquals;

import error.FichaSobreOtraFichaException;
import error.RecursosInsuficientesException;
import juego.Gaia;
import juego.Jugador;
import juego.Raza;
import juego.Tecnologia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tablero.Coordenada;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import ficha.Ficha;
import ficha.natural.recurso.Volcan;
import ficha.protoss.edificio.Asimilador;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.unidad.Zealot;

public class ContruccionTest {

    private Jugador protoss;
    private Jugador pachaMama; //vende patria
    private Tablero mapa;
    private Coordenada coordenada;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        mapa = new Tablero(20, 20);
        coordenada = new Coordenada(1, 1);
        pachaMama = mapa.gaia();

        protoss.cheats().verTodoElMapa(mapa);
    }

    @Test
    public void queTardeElTiempoCorrecto() {
        Ficha pilon = new Pilon().enConstruccion();
        pilon.setBasico(protoss, mapa, coordenada);
        pilon.ponerEnJuego();

        for (int i = 0; i < pilon.turnosParaCrear(); i++) {
            assertEquals(protoss.poblacionPosible(), 0);
            pilon.pasarTurno();
        }

        assertEquals(protoss.poblacionPosible(), pilon.poblacionQueDa);
    }

    //fichaTerrestre/casa
    @Test
    public void usarRecursosCorrectos() {
        Ficha nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }

    @Test(expected = RecursosInsuficientesException.class)
    public void falloPorFaltaDeRecursos() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 0, 0);
        Ficha nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();
    }


    @Test(expected = FichaSobreOtraFichaException.class)
    public void falloPorSobreposicion() {
        Ficha nuevoEdificio = new Pilon().enConstruccion();

        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();
    }

    @Test
    public void crearseEnLugarCorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        Assert.assertFalse(mapa.hayEspacioTerreste(coordenada));
    }

    @Test(expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoFuenteDeRecursos() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();
    }

    //recursoTerrestre

    // EdifcioDeRecursosTerrestre

    @Test
    public void crearseEnLugarCorrectoEdifcioDeRecusosTerrestre() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        Ficha nuevoEdificio = new Asimilador().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        assertEquals(200, protoss.cantidadGas());
        assertEquals(400, protoss.cantidadMineral());
    }

    @Test(expected = NoSePuedeCrearFicha.class)
    public void fallaLugarIncorrectoEdifcioDeRecusosTerrestre() {
        Ficha nuevoEdificio = new Asimilador().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();
    }

    // EdifcioDeRecursosTerrestre

    //turnos
    @Test
    public void tiempoCorrecto() {
        Ficha nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        final int turnosParaCrear = nuevoEdificio.turnosParaCrear();

        for (int i = 0; i < turnosParaCrear; i++) {
            assertEquals(0, protoss.poblacionPosible());
            nuevoEdificio.pasarTurno();
        }

        assertEquals(5, protoss.poblacionPosible());
    }


    //esto hirian en las pruevas de Extrategia Ficha.. pero al ser una clase abstarcta no se puede hacer.

    @Test
    public void muereCasaEnConstruccionNoPasaNada() {
        Ficha nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();
        protoss.agregarPoblacionTotal(10); //para que se agrege la poblacion tiene que pasar los turnos de la consturccion

        nuevoEdificio.muerete();

        assertEquals(10, protoss.poblacionPosible());
    }

    @Test
    public void muereTerrestreSeLiberaElEspacio() {
        Ficha nuevoEdificio = new Pilon().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        nuevoEdificio.muerete();

        Assert.assertTrue(mapa.hayEspacioTerreste(coordenada));
    }


    @Test
    public void muereUnidadPierdesPoblacionaActual() {
        protoss.agregarTecnologia(Tecnologia.ACCESO);

        Ficha nuevaUnidad = new Zealot().enConstruccion();
        protoss.agregarPoblacionTotal(10);
        nuevaUnidad.setBasico(protoss, mapa, coordenada);
        nuevaUnidad.ponerEnJuego();

        nuevaUnidad.muerete();

        assertEquals(0, protoss.poblcacionActual());
    }

    @Test
    public void muereFuenteDeRecursoDejaElRecurso() {
        Ficha nuevoRecurso = new Volcan();
        nuevoRecurso.setBasico(pachaMama, mapa, coordenada);
        nuevoRecurso.ponerEnJuego();

        Ficha nuevoEdificio = new Asimilador().enConstruccion();
        nuevoEdificio.setBasico(protoss, mapa, coordenada);
        nuevoEdificio.ponerEnJuego();

        nuevoEdificio.muerete();

        assertEquals(nuevoRecurso, mapa.getFichaTerrestre(coordenada));
    }

}

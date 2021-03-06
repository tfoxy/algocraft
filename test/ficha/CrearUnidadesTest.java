package ficha;

import error.CasillaFueraDeVisionException;
import error.PoblacionInsuficienteException;
import error.TecnologiaInsuficienteException;
import ficha.protoss.edificio.Acceso;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.unidad.Zealot;
import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.ITablero;
import tablero.Tablero;

public class CrearUnidadesTest {

    private ITablero mapa;
    private Jugador protoss;

    @Before
    public void initialize() {
        protoss = new Jugador("Poroto", Raza.PROTOSS, 500, 200);
        mapa = new Tablero(10, 10);
    }

    @Test
    public void puedeConstruirseSiCumpleLosRequisitos() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = PoblacionInsuficienteException.class)
    public void noPuedeConstruirseSiNoTieneLaPoblacionSuficiente() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noPuedeConstruirseSiNoTieneElEdificioNecesario() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noPuedeConstruirseSiSeDestruyeElEdificioNecesario() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        acceso.muerete();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test
    public void puedeConstruirseSiNoSeDestruyenTodosLosEdificiosQueDenLaTecnologiaNecesaria() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha otroAcceso = new Acceso();
        otroAcceso.setBasico(protoss, mapa, new Coordenada(5, 3));
        otroAcceso.ponerEnJuego();

        acceso.muerete();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noPuedeConstruirseSiSeDestruyenTodosLosEdificiosQueDenLaTecnologiaNecesaria() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha otroAcceso = new Acceso();
        otroAcceso.setBasico(protoss, mapa, new Coordenada(5, 3));
        otroAcceso.ponerEnJuego();

        acceso.muerete();
        otroAcceso.muerete();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noPuedeConstruirseSiElEdificioNecesarioEstaEnConstruccion() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso().enConstruccion();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test
    public void puedeConstruirseSiTieneElEdificioNecesarioYOtroEdificioNecesarioQueEstabaEnConstruccionEsDestruido() {
        protoss.cheats().verTodoElMapa(mapa);

        Ficha pilon = new Pilon();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();

        Ficha acceso = new Acceso();
        acceso.setBasico(protoss, mapa, new Coordenada(4, 3));
        acceso.ponerEnJuego();

        Ficha accesoEnConstruccion = new Acceso().enConstruccion();
        accesoEnConstruccion.setBasico(protoss, mapa, new Coordenada(5, 3));
        accesoEnConstruccion.ponerEnJuego();

        accesoEnConstruccion.muerete();

        Ficha unidad = new Zealot().enConstruccion();
        unidad.setBasico(protoss, mapa, new Coordenada(3, 3));
        unidad.ponerEnJuego();
    }

    @Test(expected = CasillaFueraDeVisionException.class)
    public void noPuedeConstruirseDondeNoSeVe() {
        Ficha pilon = new Pilon().enConstruccion();
        pilon.setBasico(protoss, mapa, new Coordenada(2, 3));
        pilon.ponerEnJuego();
    }

}

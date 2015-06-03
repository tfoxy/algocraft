package ficha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import Errores.NoSePuedeCrear;
import Ficha.Asimilador;
import Ficha.Ficha;
import Ficha.Pilón;
import Ficha.Zealot;
import Ficha.FichasNaturales.Volcan;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;



public class CrearUnidadesTest {


    @Test
    public void UsarRecursosCorrectos() {
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 200, 200);
        Ficha NuevoEdificio= new Pilón(Protos);
        new Zealot(Protos);
        Protos.NewFicha(NuevoEdificio);

        assertEquals(Protos.PoblcacionActual(), 2);
        assertEquals(Protos.CantidadGaz(), 200);
        assertEquals(Protos.CantidadCriztal(), 100);
    }


    @Test(expected=NoSePuedeCrear.class)
    public void NoCrearSiNohayRecurzosYTireHecepcion() {
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 200, 200);

        new Zealot(Protos);
    }


    @Test
    public void CrearCazaYQueDePoblacion() {
        //esto cocnierne a los dos grupos de Text.//
        TablaJugador Protos = new TablaJugador("Proto", "Protos");
        Ficha NuevoEdificio= new Pilón(Protos);

        Protos.NewFicha(NuevoEdificio);

        assertEquals(Protos.PoblcacionPosible(), 5);
    }


    @Test
    public void QueTardeElTiempoCorrecto() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 500, 200);
        Ficha ficha = new Pilón(Protos,Lugar, Mapa);

        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();

        assertEquals(Protos.PoblcacionPosible(), 5);
    }


    @Test
    public void QueNoTardeMenosQueTiempoCorrecto() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 500, 200);
        Ficha ficha = new Pilón(Protos, Lugar, Mapa);

        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();

        assertNotEquals(Protos.PoblcacionPosible(), 5);
    }


    @Test(expected=NoSePuedeCrear.class)
    public void FaltanTecnologias() {
        Tablero Mapa = new Tablero (10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Humanos = new TablaJugador("humanos", "humanos", 500, 200);

        new Pilón(Humanos,Lugar, Mapa);
    }


    @Test
    public void ConstruccionDeAsimiladorEnUnVolcan() {
        Tablero Mapa = new Tablero (10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 500, 200);
        new Volcan(2000, Lugar, Mapa);
        Ficha ficha = new Asimilador(Protos, Lugar, Mapa);

        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        // ElTurno que se crea tambien trabaja.

        assertNotEquals(210, Protos.CantidadGaz());
    }


    @Test(expected=NoSePuedeCrear.class)
    public void ConstruccionDeAsimiladorSinUnVolcan() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 500, 200);

        new Asimilador(Protos,Lugar, Mapa);
    }

}

package ficha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Errores.NoSePuedeCrear;
import Ficha.Asimilador;
import Ficha.Ficha;
import Ficha.FichaDeJugador;
import Ficha.Pilón;
import Ficha.Zealot;
import Ficha.FichasNaturales.Volcan;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;




public class CrearUnidadesTest {


    @Test
    public void UsarRecursosCorrectos() {
        TablaJugador Protos = new TablaJugador("Proto","Protos",200,200);
        FichaDeJugador NuevoEdificio= new Pilón(Protos);
        new Zealot(Protos);
        Protos.NewFicha(NuevoEdificio);

        assertTrue(Protos.PoblcacionActual() == 2);
        assertTrue(Protos.CantidadGaz() == 200);
        assertTrue(Protos.CantidadCriztal() == 100);
    }


    @Test
    public void NoCrearSiNohayRecurzosYTireHecepcion() {
        TablaJugador Protos = new TablaJugador("Proto","Protos",200,200);
        try {new Zealot(Protos);}
        catch(NoSePuedeCrear ex)
        {assertTrue(true);
        }
    }

    @Test
    public void CrearCazaYQueDePoblacion() {
        //esto cocnierne a los dos grupos de Text.//
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new Pilón(Protos);
        Protos.NewFicha(NuevoEdificio);
        assertTrue(Protos.PoblcacionPosible() == 5);

    }

    @Test
    public void QueTardeElTiempoCorrecto() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",500,200);
        Ficha ficha = new Pilón(Protos,Lugar, Mapa);
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        assertTrue(Protos.PoblcacionPosible() == 5);
    }

    @Test
    public void QueNoTardeMenosQueTiempoCorrecto() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",500,200);
        Ficha ficha = new Pilón(Protos,Lugar, Mapa);
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        assertFalse(Protos.PoblcacionPosible() == 5);
    }

    @Test
    public void FaltanTecnologias() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Humanos = new TablaJugador("humanos","humanos",500,200);
        try {Ficha ficha  = new Pilón(Humanos,Lugar, Mapa);}
        catch(NoSePuedeCrear ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void ConstruccionDeAsimiladorEnUnVolcan() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",500,200);
        new Volcan(2000, Lugar, Mapa);
        Ficha ficha = new Asimilador(Protos,Lugar, Mapa);

        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        ficha.PasarTurno();
        // ElTurno que se crea tambien trabaja.

        assertFalse(Protos.CantidadGaz() == 210);
    }

    @Test
    public void ConstruccionDeAsimiladorSinUnVolcan() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",500,200);
        try {new Asimilador(Protos,Lugar, Mapa);}
        catch(NoSePuedeCrear ex)
        {assertTrue(true);
        }
    }
}

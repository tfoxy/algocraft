package ficha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Errores.NoSePuedeCrear;
import Ficha.Asimilador;
import Ficha.Ficha;
import Ficha.FichaDeJugador;
import Ficha.NexoMineral;
import Ficha.Pilón;
import Ficha.Zealot;
import Jugador.TablaJugador;
import Tablero.Cordenada;
import Tablero.Tablero;



public class RecursosTest {

    @Test
    public void RecuersosPorTurnoCristaBasicol() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadCriztal() == 10);

    }

    @Test
    public void RecuersosPorTurnoCristal() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadCriztal() == 40);

    }

    @Test
    public void RecuersosPorTurnoGasBasicol() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadGaz() == 10);
    }

    @Test
    public void RecuersosPorTurnoGasyCristalBasicol() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadGaz() == 10);
        assertTrue(Protos.CantidadCriztal() == 10);
    }

    @Test
    public void RecuersosPorTurnoGasyCristalComplejo() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadGaz() == 20);
        assertTrue(Protos.CantidadCriztal() == 40);
    }

    @Test
    public void PerderFuenteDeRecursosYVerficiarQueNoLosGanesPorTurno() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        FichaDeJugador NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadGaz() == 20);
        assertTrue(Protos.CantidadCriztal() == 40);
        Protos.PerderFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertTrue(Protos.CantidadGaz() == 40);
        assertTrue(Protos.CantidadCriztal() == 60);
    }


    @Test
    public void MuereUnidadGanamosPoblacio() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",200,200);
        FichaDeJugador NuevoEdificio= new Pilón(Protos);
        Protos.AgregarTecnologia("Acceso");
        FichaDeJugador NuevaUnidad = new Zealot(Protos,Lugar,Mapa);
        Protos.NewFicha(NuevoEdificio);
        assertTrue(Protos.PoblcacionActual() == 2);
        NuevaUnidad.Muerete();
        assertTrue(Protos.PoblcacionActual() == 0);
    }

    @Test
    public void MuereCasaPerdemosPoblacio() throws NoSePuedeCrear {
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
        ficha.Muerete();
        assertTrue(Protos.PoblcacionPosible() == 0);

    }
}

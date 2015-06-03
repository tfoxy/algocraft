package ficha;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Errores.NoSePuedeCrear;
import Ficha.Asimilador;
import Ficha.Ficha;
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
        Ficha NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertEquals(Protos.CantidadCriztal(), 10);

    }

    @Test
    public void RecuersosPorTurnoCristal() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        Ficha NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertEquals(Protos.CantidadCriztal(), 40);

    }

    @Test
    public void RecuersosPorTurnoGasBasicol() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        Ficha NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertEquals(Protos.CantidadGaz(), 10);
    }

    @Test
    public void RecuersosPorTurnoGasyCristalBasicol() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        Ficha NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        assertEquals(Protos.CantidadGaz(), 10);
        assertEquals(Protos.CantidadCriztal(), 10);
    }

    @Test
    public void RecuersosPorTurnoGasyCristalComplejo() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        Ficha NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno ();
        assertEquals(Protos.CantidadGaz(), 20);
        assertEquals(Protos.CantidadCriztal(), 40);
    }

    @Test
    public void PerderFuenteDeRecursosYVerficiarQueNoLosGanesPorTurno() {
        TablaJugador Protos = new TablaJugador("Proto","Protos");
        Ficha NuevoEdificio= new Asimilador(Protos);
        Protos.NewFicha(NuevoEdificio);
        NuevoEdificio= new NexoMineral(Protos);
        Protos.NewFicha(NuevoEdificio);
        Protos.NewFicha(NuevoEdificio);
        Protos.PasarTurno ();
        Protos.PasarTurno();
        assertEquals(Protos.CantidadGaz(), 20);
        assertEquals(Protos.CantidadCriztal(), 40);
        Protos.PerderFicha(NuevoEdificio);
        Protos.PasarTurno();
        Protos.PasarTurno();
        assertEquals(Protos.CantidadGaz(), 40);
        assertEquals(Protos.CantidadCriztal(), 60);
    }


    @Test
    public void CreamosUnidadPerdemosPoblacio() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero(10, 10);
        Cordenada Lugar = new Cordenada(3, 3);
        TablaJugador Protos = new TablaJugador("Proto", "Protos", 200, 200);
        Ficha NuevoEdificio = new Pilón(Protos);
        Protos.AgregarTecnologia("Acceso");
        Protos.NewFicha(NuevoEdificio);
        Ficha NuevaUnidad = new Zealot(Protos, Lugar, Mapa);
        assertEquals(2, Protos.PoblcacionActual());
    }


    @Test
    public void MuereUnidadGanamosPoblacio() throws NoSePuedeCrear {
        Tablero Mapa = new Tablero (10,10);
        Cordenada Lugar = new Cordenada(3,3);
        TablaJugador Protos = new TablaJugador("Proto","Protos",200,200);
        Ficha NuevoEdificio= new Pilón(Protos);
        Protos.AgregarTecnologia("Acceso");
        Protos.NewFicha(NuevoEdificio);
        Ficha NuevaUnidad = new Zealot(Protos,Lugar,Mapa);
        NuevaUnidad.Muerete();
        assertEquals(0, Protos.PoblcacionActual());
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
        assertEquals(5, Protos.PoblcacionPosible());
        ficha.Muerete();
        assertEquals(0, Protos.PoblcacionPosible());

    }
}

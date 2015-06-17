package gui;

import escenario.EscenarioSimple;
import gui.modelo.TableroObservable;
import gui.vista.VentanaPrincipal;
import juego.Juego;
import juego.Jugador;
import juego.Raza;

import javax.swing.*;

public final class MainGui {

    private MainGui() {

    }

    public static void main(String[] args) {
        Juego.Builder builder = new Juego.Builder();
        EscenarioSimple escenario = new EscenarioSimple(builder);

        builder.agregarJugador(new Jugador("Player1", Raza.PROTOSS, 500, 200));
        builder.agregarJugador(new Jugador("Player2", Raza.TERRAN, 500, 200));

        TableroObservable mapa = builder.hacerTableroObservable();

        Juego juego = escenario.construir();

        JFrame ventanaPrincipal = new VentanaPrincipal(juego, mapa);
    }

}

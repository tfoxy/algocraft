package gui;

import escenario.EscenarioSimple;
import gui.vista.VentanaPrincipal;
import juego.Juego;
import juego.Jugador;
import juego.Raza;

import javax.swing.*;

public final class MainGui {

    private MainGui() {

    }

    public static void main(String[] args) {
        EscenarioSimple escenario = new EscenarioSimple();
        Juego.Builder builder = new Juego.Builder();

        builder.agregarJugador(new Jugador("Player1", Raza.PROTOSS, 500, 200));
        builder.agregarJugador(new Jugador("Player2", Raza.TERRAN, 500, 200));

        Juego juego = escenario.cargarEn(builder);

        JFrame ventanaPrincipal = new VentanaPrincipal(juego);
    }

}

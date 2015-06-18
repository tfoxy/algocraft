package gui;

import escenario.EscenarioSimple;
import ficha.Ficha;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardEvents;
import gui.modelo.TableroObservable;
import gui.vista.GrillaView;
import gui.vista.JugadorView;
import gui.vista.VentanaPrincipal;
import juego.Juego;
import juego.Jugador;
import juego.Raza;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tablero.Coordenada;
import vista.FichaView;
import controladores.ControladorFicha;

public final class MainGui {

    private MainGui() {
        // noop
    }

    public static void main(String[] args) {
        // Modelo
        Juego.Builder builder = new Juego.Builder();
        EscenarioSimple escenario = new EscenarioSimple(builder);

        builder.agregarJugador(new Jugador("Player1", Raza.PROTOSS, 500, 200));
        builder.agregarJugador(new Jugador("Player2", Raza.TERRAN, 500, 200));

        TableroObservable mapa = builder.hacerTableroObservable();

        Juego juego = escenario.construir();

        Ficha ficha = mapa.getFichaTerrestre(new Coordenada(1, 1));


        // Controladores
        KeyboardEvents keyboardEvents = new KeyboardEvents();
        ControladorJugador controladorJugador = new ControladorJugador(juego);
        ControladorFicha controladorFicha = new ControladorFicha(ficha);
        controladorFicha.listenKeyboard(keyboardEvents);

        // Vistas
        JPanel grilla = new GrillaView(mapa, controladorFicha);
        JPanel fichaView = new FichaView(controladorFicha);
        JPanel jugadorView = new JugadorView(controladorJugador);

        JFrame ventana = new VentanaPrincipal(grilla, fichaView);
        ventana.setVisible(true);
    }

}

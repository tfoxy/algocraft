package gui;

import escenario.EscenarioSimple;
import ficha.Ficha;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardEvents;
import gui.modelo.FichaObjetivo;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.TableroObservable;
import gui.vista.GrillaView;
import gui.vista.JugadorView;
import gui.vista.LoggerView;
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

        FichaObjetivo fichaObjetivo = new FichaObjetivo();
        JugadorDeTurno jugadorDeTurno = new JugadorDeTurno(juego, fichaObjetivo);
        JuegoLogger juegoLogger = new JuegoLogger();


        // Controladores
        ControladorJugador controladorJugador = new ControladorJugador(fichaObjetivo, jugadorDeTurno);
        controladorJugador.setJuegoLogger(juegoLogger);
        ControladorFicha controladorFicha = new ControladorFicha(fichaObjetivo, jugadorDeTurno);
        controladorFicha.setJuegoLogger(juegoLogger);

        jugadorDeTurno.comenzarTurno();

        // Vistas
        VentanaPrincipal.iniciarPropiedadesGlobales();

        JPanel grillaView = new GrillaView(mapa, fichaObjetivo);
        JPanel fichaView = new FichaView(controladorFicha);
        JPanel jugadorView = new JugadorView(controladorJugador);
        JPanel loggerView = new LoggerView(juegoLogger);

        JFrame ventana = new VentanaPrincipal(grillaView, fichaView, jugadorView, loggerView);
        ventana.setVisible(true);
    }

}

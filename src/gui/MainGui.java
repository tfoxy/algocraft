package gui;

import escenario.EscenarioSimple;
import ficha.Ficha;
import gui.modelo.ObservableElement;
import gui.modelo.TableroObservable;
import gui.vista.GrillaView;
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

        ObservableElement<Ficha> fichaSeleccionada =
                new ObservableElement<>(mapa.getFichaTerrestre(new Coordenada(1, 1)));

        // Controladores
        ControladorFicha controladorFicha = new ControladorFicha(fichaSeleccionada);

        // Vistas
        JPanel grilla = new GrillaView(mapa, fichaSeleccionada);
        JPanel fichaView = new FichaView(controladorFicha);

        JFrame ventana = new VentanaPrincipal(grilla, fichaView);
        ventana.setVisible(true);
    }

}

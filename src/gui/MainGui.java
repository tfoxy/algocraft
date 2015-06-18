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

import tablero.Coordenada;
import vista.VentanaFicha;
import controladores.ControladorFicha;

import java.util.Observable;
import java.util.Observer;

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
        JFrame grilla = new GrillaView(mapa, fichaSeleccionada);
        new VentanaFicha(controladorFicha);

        new VentanaPrincipal(grilla).mostrar();
    }

}

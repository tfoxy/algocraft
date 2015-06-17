package gui;

import escenario.EscenarioSimple;
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

public final class MainGui {

    private MainGui() {
        // noop
    }

    public static void main(String[] args) {
        Juego.Builder builder = new Juego.Builder();
        EscenarioSimple escenario = new EscenarioSimple(builder);

        builder.agregarJugador(new Jugador("Player1", Raza.PROTOSS, 500, 200));
        builder.agregarJugador(new Jugador("Player2", Raza.TERRAN, 500, 200));

        TableroObservable mapa = builder.hacerTableroObservable();

        Juego juego = escenario.construir();

        JFrame grilla = new GrillaView(mapa);

        new VentanaPrincipal(grilla).mostrar();
       
        
		ControladorFicha controladorFicha = new ControladorFicha (juego.tablero().getFichaTerrestre(new Coordenada(2,6))); //creo controlador para la ficha
		new VentanaFicha (juego.tablero().getFichaTerrestre(new Coordenada(2,6)), controladorFicha);//creo Ventana para la ficha
    }

}

package gui.controlador;

import escenario.EscenarioSimple;
import gui.modelo.FichaObjetivo;
import gui.modelo.FichaSeleccionada;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.TableroObservable;
import gui.vista.GrillaView;
import gui.vista.JugadorView;
import gui.vista.LoggerView;
import gui.vista.VentanaPrincipal;
import gui.vista.VistaInicio;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.Juego;
import juego.Jugador;
import juego.Raza;
import controladores.ControladorFicha;

import tablero.Direccion;
import vista.FichaView;

public class ControladorInicio {

    private final class JugadorListener extends AnyEventListener {
        private final VistaInicio vistaInicio;

        private JugadorListener(VistaInicio vistaInicio) {
            this.vistaInicio = vistaInicio;
        }

        @Override
        public void eventOcurred(AWTEvent e) {//si es el MainGui.
            Juego.Builder builder = new Juego.Builder();
            EscenarioSimple escenario = new EscenarioSimple(builder);

            Jugador jugador1 = new Jugador(vistaInicio.getNombre1(), vistaInicio.getRaza1(), 500, 200); //para probar "unitarimante"
            builder.agregarJugador(jugador1);
            builder.agregarJugador(new Jugador(vistaInicio.getNombre2(), vistaInicio.getRaza2(), 500, 200));

            TableroObservable mapa = builder.hacerTableroObservable();

            Juego juego = escenario.construir();

            JuegoLogger juegoLogger = new JuegoLogger();
            FichaObjetivo fichaObjetivo = new FichaObjetivo();
            JugadorDeTurno jugadorDeTurno = new JugadorDeTurno(juego, fichaObjetivo);
            jugadorDeTurno.setJuegoLogger(juegoLogger);
            FichaSeleccionada fichaSeleccionada = new FichaSeleccionada(fichaObjetivo, jugadorDeTurno);
            fichaSeleccionada.setJuegoLogger(juegoLogger);


            // Controladores
            ControladorJugador controladorJugador = new ControladorJugador(jugadorDeTurno);
            ControladorFicha controladorFicha = new ControladorFicha(fichaSeleccionada);

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

	public ActionListener jugarListener(VistaInicio vistaInicio) {
		return new JugadorListener(vistaInicio);
	}
	

}

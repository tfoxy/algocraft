package gui;

import gui.controlador.ControladorFicha;
import gui.controlador.ControladorInicio;
import gui.controlador.ControladorJugador;
import gui.modelo.ConfiguracionDeInicio;
import gui.modelo.FichaObjetivo;
import gui.modelo.FichaSeleccionada;
import gui.modelo.JuegoLogger;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;
import gui.modelo.TableroObservable;
import gui.vista.FichaView;
import gui.vista.GrillaView;
import gui.vista.JuegoView;
import gui.vista.JugadorView;
import gui.vista.LoggerView;
import gui.vista.VistaInicio;
import juego.Juego;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import java.awt.Container;

public class VentanaPrincipal extends JFrame {

    private final JuegoLogger logger;
    private final LoggerView loggerView;
    private final Container container;

    public VentanaPrincipal() {
        iniciarPropiedadesGlobales();
        setPropiedadesDeVentana();

        logger = new JuegoLogger();
        loggerView = new LoggerView(logger);
        container = this.getContentPane();

        reemplazarPanel(new TituloView());
        ponerMusica();
    }

    public static void iniciarPropiedadesGlobales() {
        // Mostrar Tooltips inmediatamente (sin delay)
        ToolTipManager.sharedInstance().setInitialDelay(0);

        // enable anti-aliased text:
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
    }

    private void setPropiedadesDeVentana() {
        setTitle("AlgoCraft");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private static class TituloView extends JPanel {
        private TituloView() {
            add(new JLabel("ALGOCRAFT"));
        }
    }

    private static class CargaView extends JPanel {
        private CargaView(String msg) {
            add(new JLabel("CARGANDO " + msg + "..."));
        }
    }

    private void reemplazarPanel(JPanel panel) {
        container.removeAll();
        container.add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void ponerMusica() {
        /*URL url = null;
        try {
			url = new URL("file:///home/geco/git/algocraft/src/gui/vista/Musica.wav");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioClip sonido = Applet.newAudioClip(url);
		sonido.loop();
		Por si despues se quiere seguir intentando. pero por lo pronto se tendria que borrar.
		*/
    }

    public void mostrarInicio() {
        reemplazarPanel(new CargaView("Inicio"));

        ConfiguracionDeInicio configJuego = new ConfiguracionDeInicio();
        configJuego.setLogger(logger);
        configJuego.getInicioDeJuegoObservable().addObserver(new InicioDeJuego());

        ControladorInicio control = new ControladorInicio(configJuego);

        JPanel panel = new VistaInicio(configJuego, control, loggerView);
        reemplazarPanel(panel);
    }

    private class InicioDeJuego implements Observer<Juego> {
        @Override
        public void update(Observable<Juego> object, Juego data) {
            iniciarJuego(data);
        }
    }

    private void iniciarJuego(Juego juego) {
        reemplazarPanel(new CargaView("Juego"));

        // Modelo
        TableroObservable mapa = (TableroObservable) juego.tablero();

        FichaObjetivo fichaObjetivo = new FichaObjetivo();
        JugadorDeTurno jugadorDeTurno = new JugadorDeTurno(juego, fichaObjetivo);
        jugadorDeTurno.setJuegoLogger(logger);
        FichaSeleccionada fichaSeleccionada = new FichaSeleccionada(fichaObjetivo, jugadorDeTurno);
        fichaSeleccionada.setJuegoLogger(logger);

        // Controladores
        ControladorJugador controladorJugador = new ControladorJugador(jugadorDeTurno);
        ControladorFicha controladorFicha = new ControladorFicha(fichaSeleccionada);

        // Vistas
        JPanel grillaView = new GrillaView(mapa, fichaObjetivo, jugadorDeTurno);
        JPanel fichaView = new FichaView(controladorFicha);
        JPanel jugadorView = new JugadorView(controladorJugador);

        jugadorDeTurno.comenzarTurno();

        JPanel juegoView = new JuegoView(grillaView, fichaView, jugadorView, loggerView);
        reemplazarPanel(juegoView);
    }
}

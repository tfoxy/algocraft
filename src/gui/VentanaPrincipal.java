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
import gui.vista.VistaGanador;
import gui.vista.VistaInicio;
import juego.Juego;
import juego.Jugador;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class VentanaPrincipal extends JFrame {

    private final JuegoLogger logger;
    private final LoggerView loggerView;
    private final Container container;

    public VentanaPrincipal() {
        setPropiedadesDeVentana();

        logger = new JuegoLogger();
        loggerView = new LoggerView(logger);
        container = this.getContentPane();

        reemplazarContenido(new TituloView());
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

    private static final class TituloView extends JLabel {
        private TituloView() {
            super("ALGOCRAFT");
        }
    }

    private static final class CargaView extends JLabel {
        private CargaView(String msg) {
            super("CARGANDO " + msg + "...");
        }
    }

    private void reemplazarContenido(JComponent component) {
        container.removeAll();
        container.add(component);
        pack();
        setLocationRelativeTo(null);
    }

    private void ponerMusica() {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("music/music.wav");
            if (inputStream != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarInicio() {
        reemplazarContenido(new CargaView("Inicio"));

        ConfiguracionDeInicio configJuego = new ConfiguracionDeInicio();
        configJuego.setLogger(logger);
        configJuego.getInicioDeJuegoObservable().addObserver(new InicioDeJuego());

        ControladorInicio control = new ControladorInicio(configJuego);

        JPanel panel = new VistaInicio(configJuego, control, loggerView);
        reemplazarContenido(panel);
    }

    private class InicioDeJuego implements Observer<Juego> {
        @Override
        public void update(Observable<Juego> object, Juego data) {
            iniciarJuego(data);
        }
    }

    private void iniciarJuego(Juego juego) {
        reemplazarContenido(new CargaView("Juego"));

        // Modelo
        TableroObservable mapa = (TableroObservable) juego.tablero();

        FichaObjetivo fichaObjetivo = new FichaObjetivo();
        JugadorDeTurno jugadorDeTurno = new JugadorDeTurno(juego, fichaObjetivo);
        jugadorDeTurno.setJuegoLogger(logger);
        jugadorDeTurno.jugadorGanoObservable().addObserver(new VictoriaDeJugadorObserver());

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
        reemplazarContenido(juegoView);
    }


    private class VictoriaDeJugadorObserver implements Observer<JugadorDeTurno> {
        @Override
        public void update(Observable<JugadorDeTurno> object,
                           JugadorDeTurno data) {
            mostrarGanador(data.jugador());
        }
    }

    private void mostrarGanador(Jugador jugador) {
        JPanel ganadorPanel = new VistaGanador(jugador, new MostrarInicioListener());

        reemplazarContenido(ganadorPanel);
    }

    private class MostrarInicioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarInicio();
        }
    }
}

package gui.modelo;

import error.JuegoException;
import escenario.EscenarioSimple;
import juego.ColorDeJugador;
import juego.Juego;
import juego.Jugador;
import juego.Raza;
import juego.Recursos;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDeInicio {

    private final List<PropiedadesDeJugador> jugadores;
    private final Observable<Juego> inicioDeJuegoObservable;
    private JuegoLogger logger = JuegoLogger.EMPTY;

    public ConfiguracionDeInicio() {
        this.jugadores = new ArrayList<>();
        this.jugadores.add(new PropiedadesDeJugador("Player1", Raza.PROTOSS, ColorDeJugador.AZUL));
        this.jugadores.add(new PropiedadesDeJugador("Player2", Raza.TERRAN, ColorDeJugador.ROJO));

        inicioDeJuegoObservable = new Observable<>();
    }

    public List<PropiedadesDeJugador> getJugadores() {
        return jugadores;
    }

    public Observable<Juego> getInicioDeJuegoObservable() {
        return inicioDeJuegoObservable;
    }

    public void setLogger(JuegoLogger logger) {
        this.logger = logger;
    }

    private Juego construirJuego() {
        Juego.Builder builder = new Juego.Builder();
        EscenarioSimple escenario = new EscenarioSimple(builder);

        Recursos recursos = new Recursos(500, 200);

        for (PropiedadesDeJugador props: jugadores) {
            Jugador jugador = props.construirJugador(recursos);
            builder.agregarJugador(jugador);
        }

        builder.hacerTableroObservable();

        return escenario.construir();
    }

    public void iniciarJuego() {
        try {
            Juego juego = construirJuego();
            inicioDeJuegoObservable.notifyObservers(juego);
        } catch (JuegoException e) {
            logger.log(e);
        }
    }
}

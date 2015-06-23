package gui.modelo;

import error.JuegoException;
import ficha.Ficha;
import gui.vista.VistaGanador;
import gui.vista.VistaPerdedor;
import juego.Juego;
import juego.Jugador;
import tablero.Coordenada;

import java.util.NoSuchElementException;

public class JugadorDeTurno {

    private final Juego juego;
    private final FichaObjetivo fichaObjetivo;
    private final FichaParaConstruir fichaParaConstruir;
    private final Observable<JugadorDeTurno> comenzarTurnoObservable;
    private final Observable<JugadorDeTurno> terminarTurnoObservable;
    private final Observable<JugadorDeTurno> jugadorGanoObservable;
    
    private JuegoLogger juegoLogger = JuegoLogger.EMPTY;

    public JugadorDeTurno(Juego juego, FichaObjetivo fichaObjetivo) {
        this.juego = juego;
        this.fichaObjetivo = fichaObjetivo;
        this.comenzarTurnoObservable = new Observable<>();
        this.terminarTurnoObservable = new Observable<>();
        this.jugadorGanoObservable = new Observable<>(); 
        this.fichaParaConstruir = new FichaParaConstruir(fichaObjetivo, this);

        this.comenzarTurnoObservable.addObserver(new SeleccionarPrimeraFicha());
        fichaObjetivo.fichaObservables().on(AccionEnGrilla.CONSTRUCCION,
                new UbicarFichaParaConstruirObserver());
    }


    private class SeleccionarPrimeraFicha implements Observer<JugadorDeTurno> {
        @Override
        public void update(Observable<JugadorDeTurno> object, JugadorDeTurno data) {
            fichaObjetivo.cambiarAccion(AccionEnGrilla.SELECCION);
            fichaObjetivo.cambiarFicha(elegirNuevaFicha());
        }
    }

    private class UbicarFichaParaConstruirObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            try {
                fichaParaConstruir.ubicarEn(data.ficha().coordenada());
            } catch (JuegoException e) {
                juegoLogger.log(e);
            }
        }
    }


    private Ficha elegirNuevaFicha() {
        try {
            return jugador().fichas().iterator().next();
        } catch (NoSuchElementException exception) {
            return juego.tablero().getFichaTerrestre(new Coordenada(1, 1));
        }
    }


    public Jugador jugador() {
        return juego.jugadorActual();
    }

    public Juego juego() {
        return juego;
    }

    public FichaParaConstruir fichaParaConstruir() {
        return fichaParaConstruir;
    }

    public void terminarTurno() {
        this.terminarTurnoObservable.notifyObservers(this);

        String name =juego.jugadorActual().nombre(); 
        this.juego.pasarJugador();
        if (juego.jugadorActual().perdi())
        { //new VistaPerdedor(juego.jugadorActual()); 
        //new VistaGanador(name);
        
        this.jugadorGanoObservable.notifyObservers(this);}
        
        this.comenzarTurnoObservable.notifyObservers(this);
    }

    public Observable<JugadorDeTurno> jugadorGanoObservable() {
        return jugadorGanoObservable;
    }
    
    public Observable<JugadorDeTurno> comenzarTurnoObservable() {
        return comenzarTurnoObservable;
    }

    public Observable<JugadorDeTurno> terminarTurnoObservable() {
        return terminarTurnoObservable;
    }

    public void comenzarTurno() {
        this.comenzarTurnoObservable.notifyObservers(this);
    }

    public void setJuegoLogger(JuegoLogger juegoLogger) {
        this.juegoLogger = juegoLogger;
    }
}

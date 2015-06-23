package gui.modelo;

import error.JuegoException;
import error.UnicamenteObjetivoPropioException;
import ficha.Ficha;
import magia.Magia;
import tablero.Direccion;

public class FichaSeleccionada {
    private final FichaObjetivo fichaObjetivo;
    private final JugadorDeTurno jugadorDeTurno;
    private final FichasCargadas fichasCargadas;
    private final MagiasDisponibles magiasDisponibles;
    private final Observable<Ficha> cambioDeFichaObservable;
    private final ObservableActions<AccionDeFicha, FichaSeleccionada> accionObservables;
    private JuegoLogger logger = JuegoLogger.EMPTY;

    private Ficha ficha;

    public FichaSeleccionada(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.fichaObjetivo = fichaObjetivo;
        this.jugadorDeTurno = jugadorDeTurno;

        this.fichasCargadas = new FichasCargadas(this);
        this.magiasDisponibles = new MagiasDisponibles(fichaObjetivo);

        this.cambioDeFichaObservable = new Observable<>();
        this.accionObservables = new ObservableEnumActions<>(AccionDeFicha.class);

        ObservableActions<AccionEnGrilla, FichaObjetivo> observables
                = fichaObjetivo.fichaObservables();
        observables.on(AccionEnGrilla.SELECCION, new SeleccionObserver());
        observables.on(AccionEnGrilla.ATAQUE, new AtaqueObserver());
        observables.on(AccionEnGrilla.EMISION_DE_MAGIA, new EmisionDeMagiaObserver());
    }


    private class SeleccionObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            seleccionar(data.ficha());
        }
    }

    private class AtaqueObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            atacar(data.ficha());
        }
    }

    private class EmisionDeMagiaObserver implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            aplicarMagia(data.ficha());
        }
    }


    public void seleccionar(Ficha ficha) {
        this.ficha = ficha;
        fichasCargadas.cambiarTransporte(ficha);
        magiasDisponibles.cambiarCaster(ficha);
        cambioDeFichaObservable.notifyObservers(ficha);
    }

    public void moverHacia(Direccion direccion) {
        try {
            validarPropietario("Solamente se pueden mover unidades propias");
            ficha().mover(direccion);
            fichaObjetivo.cambiarAccion(AccionEnGrilla.SELECCION);
            fichaObjetivo.cambiarFicha(ficha());
            accionObservables.notify(AccionDeFicha.MOVIMIENTO, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public void modoAtaque() {
        fichaObjetivo.cambiarAccion(AccionEnGrilla.ATAQUE);
    }

    public void atacar(Ficha objetivo) {
        try {
            validarPropietario("Solamente se puede atacar con unidades propias");
            ficha().atacar(objetivo);
            String msg = "Ataque a " + objetivo.nombre() + " " + objetivo.barras().toShortString();
            logger.log(msg);
            accionObservables.notify(AccionDeFicha.ATAQUE, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public void aplicarMagia(Ficha objetivo) {
        try {
            validarPropietario("Solamente se puede realizar magia con unidades propias");
            magiasDisponibles.aplicarEn(objetivo.coordenada());
            accionObservables.notify(AccionDeFicha.EMISION_DE_MAGIA, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public void cargar() {
        try {
            validarPropietario("Solamente se puede ordenar cargar a unidades propias");
            ficha().cargar();
            fichasCargadas.fireAdded();
            accionObservables.notify(AccionDeFicha.CARGAR, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public void descargar(Ficha ficha) {
        try {
            validarPropietario("Solamente se puede ordenar descargar a unidades propias");
            ficha().descargar(ficha);
            fichasCargadas.fireRemoved();
            accionObservables.notify(AccionDeFicha.DESCARGAR, this);
        } catch (JuegoException exc) {
            logger.log(exc);
        }
    }

    public FichasCargadas fichasCargadas() {
        return fichasCargadas;
    }

    public MagiasDisponibles magiasDisponibles() {
        return magiasDisponibles;
    }

    public Observable<Ficha> cambioDeFichaObservable() {
        return cambioDeFichaObservable;
    }

    public ObservableActions<AccionDeFicha, FichaSeleccionada> accionObservables() {
        return accionObservables;
    }

    public Ficha ficha() {
        return ficha;
    }

    public void setJuegoLogger(JuegoLogger juegoLogger) {
        this.logger = juegoLogger;
    }

    public void validarPropietario() {
        validarPropietario("Unidad no es propia");
    }

    public void validarPropietario(String errorMsg) {
        if (!ficha().propietario().equals(jugadorDeTurno.jugador())) {
            throw new UnicamenteObjetivoPropioException(errorMsg);
        }
    }
}

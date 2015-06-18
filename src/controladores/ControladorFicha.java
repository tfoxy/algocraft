package controladores;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;

import error.JuegoException;
import gui.controlador.AnyEventListener;
import gui.controlador.ControladorJugador;
import gui.controlador.KeyboardEvents;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import juego.Jugador;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;

import ficha.Ficha;
import tablero.ITablero;
import tablero.Tablero;

public class ControladorFicha {

    private final ITablero mapa;
    private final ControladorJugador controladorJugador;
    private final ElementObservable<Ficha> cambioDeFichaObservable;
    private final ElementObservable<Coordenada3d> movimientoObservable;

    public ControladorFicha(ITablero mapa, ControladorJugador controladorJugador) {
        this.mapa = mapa;
        this.controladorJugador = controladorJugador;
        this.cambioDeFichaObservable = new ElementObservable<>(elegirNuevaFicha());
        this.movimientoObservable = new ElementObservable<>(ficha().coordenada());

        controladorJugador.observarCambioDeJugador(new ElementObserver<Jugador>() {
            @Override
            public void update(ElementObservable<Jugador> o, Jugador prevElement) {
                seleccionar(elegirNuevaFicha());
            }
        });
    }

    private Ficha elegirNuevaFicha() {
        Collection<Ficha> fichas = controladorJugador.jugador().fichas();
        Ficha ficha;
        try {
            ficha = fichas.iterator().next();
        } catch (NoSuchElementException exception) {
            ficha = mapa.getFichaTerrestre(new Coordenada(1, 1));
        }

        return ficha;
    }

    public Ficha ficha() {
        return cambioDeFichaObservable.get();
    }

    public void seleccionar(Ficha ficha) {
        cambioDeFichaObservable.setAndNotify(ficha);
        movimientoObservable.set(ficha.coordenada());
    }

    public void observarCambioDeFicha(ElementObserver<Ficha> o) {
        cambioDeFichaObservable.addObserver(o);
    }

    public void observarMovimiento(ElementObserver<Coordenada3d> o) {
        movimientoObservable.addObserver(o);
    }

    public void listenKeyboard(KeyboardEvents keyboard) {
        final int ev = KeyEvent.KEY_PRESSED;

        keyboard.addListener(ev, KeyEvent.VK_UP, new MovimientoListener(Direccion.ARRIBA));
        keyboard.addListener(ev, KeyEvent.VK_DOWN, new MovimientoListener(Direccion.ABAJO));
        keyboard.addListener(ev, KeyEvent.VK_RIGHT, new MovimientoListener(Direccion.DERECHA));
        keyboard.addListener(ev, KeyEvent.VK_LEFT, new MovimientoListener(Direccion.IZQUIERDA));
    }


    //mover en todas las direcciones XD
    private class MovimientoListener extends AnyEventListener {

        private Direccion direccion;

        private MovimientoListener(Direccion direccion) {
            this.direccion = direccion;
        }

        @Override
        public void eventOcurred(AWTEvent e) {
            // No se puede mover fichas de otro jugador
            if (!ficha().propietario().equals(controladorJugador.jugador()))
                return;

            try {
                ficha().mover(direccion);
                movimientoObservable.setAndNotify(ficha().coordenada());
            } catch (JuegoException exc) {
                // TODO avisar que no se pudo mover
            }
        }
    }

    public AnyEventListener getMovimientoListener(Direccion direccion) {
        return new MovimientoListener(direccion);
    }
}

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
import gui.modelo.AccionEnGrilla;
import gui.modelo.ElementObservable;
import gui.modelo.ElementObserver;
import gui.modelo.FichaObjetivo;
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
    private final ElementObservable<Ficha> ataqueObservable;
    private final FichaObjetivo fichaObjetivo;

    public ControladorFicha(ITablero mapa, ControladorJugador controladorJugador,
                            final FichaObjetivo fichaObjetivo) {
        this.mapa = mapa;
        this.controladorJugador = controladorJugador;
        this.cambioDeFichaObservable = new ElementObservable<>(elegirNuevaFicha());
        this.movimientoObservable = new ElementObservable<>(ficha().coordenada());
        ataqueObservable = new ElementObservable<>(null);
        this.fichaObjetivo = fichaObjetivo;

        controladorJugador.observarCambioDeJugador(new ElementObserver<Jugador>() {
            @Override
            public void update(ElementObservable<Jugador> o, Jugador prevElement) {
                seleccionar(elegirNuevaFicha());
            }
        });

        fichaObjetivo.escucharCambioDeFichaObjetivo(new ElementObserver<Ficha>() {
            @Override
            public void update(ElementObservable<Ficha> o, Ficha prevElement) {
                Ficha objetivo = o.get();
                switch(fichaObjetivo.accion()) {
                    case SELECCIONAR:
                        seleccionar(objetivo);
                        return;
                    case ATACAR:
                        atacar(objetivo);
                        return;
                }
            }
        });
    }

    public void modoAtaque() {
        fichaObjetivo.cambiarAccion(AccionEnGrilla.ATACAR);
    }

    private void atacar(Ficha objetivo) {
        try {
            ficha().atacar(objetivo);
            ataqueObservable.setAndNotify(objetivo);
        } catch (JuegoException exc) {
            // TODO mostrar error
        }
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

    public void observarAtaque(ElementObserver<Ficha> elementObserver) {
        ataqueObservable.addObserver(elementObserver);
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

    public AnyEventListener getAtaqueListener() {
        return new AnyEventListener() {
            @Override
            public void eventOcurred(AWTEvent e) {
                modoAtaque();
            }
        };
    }
}

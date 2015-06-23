package gui.vista;

import ficha.Ficha;
import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.JugadorDeTurno;
import gui.modelo.Observable;
import gui.modelo.Observer;
import gui.modelo.TableroObservable;
import javafx.collections.SetChangeListener;
import tablero.Coordenada;
import tablero.VisibilidadDelJugador;

import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GrillaView extends JPanel {

    private static final Map<AccionEnGrilla, Cursor> CURSORS = new EnumMap<>(AccionEnGrilla.class);

    static {
        CURSORS.put(AccionEnGrilla.SELECCION, Cursor.getDefaultCursor());
        CURSORS.put(AccionEnGrilla.ATAQUE, new Cursor(Cursor.CROSSHAIR_CURSOR));
        CURSORS.put(AccionEnGrilla.EMISION_DE_MAGIA, new Cursor(Cursor.WAIT_CURSOR));
        CURSORS.put(AccionEnGrilla.CONSTRUCCION, new Cursor(Cursor.HAND_CURSOR));
    }


    private final Map<Coordenada, CasillaVista> casillas = new HashMap<>();
    private final SetChangeListener<Coordenada> actualizarVisiblidad =
            new ActualizarVisibilidad();
    private VisibilidadDelJugador visibilidad;


    public GrillaView(TableroObservable mapa, FichaObjetivo fichaObjetivo,
                      JugadorDeTurno jugadorDeTurno) {
        super(new GridLayout(mapa.getLongitudY(), mapa.getLongitudX()));

        for (int y = mapa.getLongitudY(); y > 0; y--) {
            for (int x = 1; x <= mapa.getLongitudX(); x++) {
                Coordenada coordenada = new Coordenada(x, y);
                CasillaVista casilla =
                        new CasillaVista(coordenada, mapa, fichaObjetivo);

                casillas.put(coordenada, casilla);
                add(casilla);
            }
        }

        setFocusable(true);

        mapa.addObserver(new ActualizarCasilla());

        fichaObjetivo.accionObservable().addObserver(new CambioDeCursor());
        fichaObjetivo.fichaObservables().addObserver(new FocusEnGrilla());

        visibilidad = jugadorDeTurno.jugador().visibilidad();
        jugadorDeTurno.comenzarTurnoObservable().addObserver(new ComenzarTurno());
    }


    private class CambioDeCursor implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            Cursor cursor = CURSORS.get(data.accion());
            setCursor(cursor);
        }
    }

    private class FocusEnGrilla implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            requestFocus();
        }
    }

    private class ActualizarVisibilidad implements SetChangeListener<Coordenada> {
        @Override
        public void onChanged(Change<? extends Coordenada> change) {
            Coordenada coordenada = change.getElementAdded();
            casillas.get(coordenada).mostrar(true);
        }
    }

    private class ActualizarCasilla implements Observer<Ficha> {
        @Override
        public void update(Observable<Ficha> object, Ficha data) {
            Coordenada coordenada = data.coordenada().proyeccion();
            casillas.get(coordenada).actualizar(data);
        }
    }

    private class ComenzarTurno implements Observer<JugadorDeTurno> {
        private void actualizarVisibilidadDeCasillas() {
            for (Map.Entry<Coordenada, CasillaVista> entry: casillas.entrySet()) {
                entry.getValue().mostrar(visibilidad.puedeVer(entry.getKey()));
            }
        }

        @Override
        public void update(Observable<JugadorDeTurno> object, JugadorDeTurno data) {
            visibilidad.removeListener(actualizarVisiblidad);
            visibilidad = data.jugador().visibilidad();
            visibilidad.addListener(actualizarVisiblidad);
            actualizarVisibilidadDeCasillas();
        }
    }
}

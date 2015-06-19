package gui.vista;

import gui.modelo.AccionEnGrilla;
import gui.modelo.FichaObjetivo;
import gui.modelo.Observable;
import gui.modelo.Observer;
import gui.modelo.TableroObservable;
import tablero.Coordenada;

import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;

public class GrillaView extends JPanel {

    private static final Map<AccionEnGrilla, Cursor> CURSORS = new EnumMap<>(AccionEnGrilla.class);

    static {
        CURSORS.put(AccionEnGrilla.SELECCION, Cursor.getDefaultCursor());
        CURSORS.put(AccionEnGrilla.ATAQUE, new Cursor(Cursor.CROSSHAIR_CURSOR));
        CURSORS.put(AccionEnGrilla.EMISION_DE_MAGIA, new Cursor(Cursor.WAIT_CURSOR));
        CURSORS.put(AccionEnGrilla.CONSTRUCCION, new Cursor(Cursor.HAND_CURSOR));
    }


    public GrillaView(TableroObservable mapa, FichaObjetivo fichaObjetivo) {
        setLayout(new GridLayout(mapa.getLongitudY(), mapa.getLongitudX()));

        for (int y = mapa.getLongitudY(); y > 0; y--) {
            for (int x = 1; x <= mapa.getLongitudX(); x++) {

                add(new CasillaVista(new Coordenada(x, y), mapa, fichaObjetivo));

            }
        }

        setFocusable(true);

        fichaObjetivo.accionObservable().addObserver(new CambioDeCursor());
    }


    private class CambioDeCursor implements Observer<FichaObjetivo> {
        @Override
        public void update(Observable<FichaObjetivo> object, FichaObjetivo data) {
            Cursor cursor = CURSORS.get(data.accion());
            setCursor(cursor);
        }
    }

}

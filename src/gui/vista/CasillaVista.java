package gui.vista;

import ficha.Ficha;
import ficha.TipoDeFicha;
import gui.modelo.FichaObjetivo;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

public class CasillaVista extends JPanel {

    private static final Border DEFAULT_BORDER =
            BorderFactory.createRaisedSoftBevelBorder();
    private static final Map<Color, Border> BORDERS_CACHE = new HashMap<>();

    private CasillaParaFicha tierra;
    private CasillaParaFicha aire;

    public CasillaVista(Coordenada coordenada, TableroObservable mapa,
                        FichaObjetivo fichaObjetivo) {
        tierra = new CasillaParaFicha(mapa.getFichaTerrestre(coordenada), fichaObjetivo);
        aire = new CasillaParaFicha(mapa.getFichaAerea(coordenada), fichaObjetivo);

        mapa.addObserver(new JCasillaObserver(coordenada));

        setLayout(new GridLayout(2, 1));
        setBorder(DEFAULT_BORDER);

        add(aire);
        add(tierra);
    }

    private void repaintWith(Ficha ficha) {
        CasillaParaFicha casillaParaFicha;

        if (ficha.coordenada().z == Altura.TIERRA)
            casillaParaFicha = tierra;
        else if (ficha.coordenada().z == Altura.AIRE)
            casillaParaFicha = aire;
        else if (ficha.coordenada().z == Altura.CIELO) {
            cambiarBorde(ficha);
            return;
        } else return;

        casillaParaFicha.cambiarFicha(ficha);
    }

    private Border getBorde(Color color) {
        Border border = BORDERS_CACHE.get(color);
        if (border == null) {
            border = new SoftBevelBorder(BevelBorder.RAISED, color, color.darker());
            BORDERS_CACHE.put(color, border);
        }
        return border;
    }

    private void cambiarBorde(Ficha ficha) {
        final Border border;
        if (ficha.es(TipoDeFicha.VACIA)) {
            border = DEFAULT_BORDER;
        } else {
            border = getBorde(ficha.miColor());
        }
        setBorder(border);
    }

    private class JCasillaObserver extends CasillaObserver {
        public JCasillaObserver(Coordenada coordenada) {
            super(coordenada);
        }

        @Override
        protected void updateVista(Ficha fichaNueva) {
            repaintWith(fichaNueva);
        }
    }

}

package gui.vista;

import ficha.Ficha;
import ficha.TipoDeFicha;
import gui.modelo.FichaObjetivo;
import gui.modelo.TableroObservable;
import tablero.Altura;
import tablero.Coordenada;
import tablero.ITablero;

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

    private static final Color DEFAULT_BACKGROUND_COLOR = new JPanel().getBackground();
    private static final Border DEFAULT_BORDER =
            BorderFactory.createRaisedSoftBevelBorder();

    private boolean estaVisible;
    private CasillaParaFicha tierra;
    private CasillaParaFicha aire;

    public CasillaVista(Coordenada coordenada, ITablero mapa, FichaObjetivo fichaObjetivo) {
        tierra = new CasillaParaFicha(mapa.getFichaTerrestre(coordenada), fichaObjetivo);
        aire = new CasillaParaFicha(mapa.getFichaAerea(coordenada), fichaObjetivo);
        cambiarBorde(mapa.getFichaCelestial(coordenada));

        setLayout(new GridLayout(2, 1));

        add(aire);
        add(tierra);
    }

    private Border getBorde(Color color) {
        return new SoftBevelBorder(BevelBorder.RAISED, color, color.darker());
    }

    private void cambiarBorde(Ficha ficha) {
        final Border border;
        final Color bgColor;
        if (ficha.es(TipoDeFicha.VACIA)) {
            border = DEFAULT_BORDER;
            bgColor = DEFAULT_BACKGROUND_COLOR;
        } else {
            border = getBorde(ficha.miColor());
            bgColor = ficha.miColor().brighter();
        }
        setBorder(border);
        setBackground(bgColor);
    }

    public void mostrar(boolean mostrar) {
        if (mostrar == estaVisible)
            return;

        if (mostrar) {
            // TODO
        } else {
            // TODO
        }

        estaVisible = mostrar;
    }

    public void actualizar(Ficha ficha) {
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
}

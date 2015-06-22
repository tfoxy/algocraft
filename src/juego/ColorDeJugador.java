package juego;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ColorDeJugador {
    private ColorDeJugador() {
        // noop
    }

    public static final Color AZUL = Color.BLUE;
    public static final Color ROJO = Color.RED;
    public static final Color VERDE = Color.GREEN;
    public static final Color AMARILLO = Color.YELLOW;
    public static final Color CYAN = Color.CYAN;
    public static final Color PURPURA = new Color(128, 0, 128);
    public static final Color NARANJA = Color.ORANGE;

    public static final List<Color> LISTA = Collections.unmodifiableList(
            Arrays.asList(
                    AZUL,
                    ROJO,
                    VERDE,
                    AMARILLO,
                    CYAN,
                    PURPURA,
                    NARANJA
            )
    );
}

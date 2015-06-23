package tablero;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Altura {
    private Altura() {
        // noop
    }

    public static final int TIERRA = 1;
    public static final int AIRE = 2;
    public static final int CIELO = 3;

    public static final List<Integer> VALORES = Arrays.asList(
            TIERRA,
            AIRE,
            CIELO
    );

    public static final int MINIMA = Collections.min(VALORES);
    public static final int MAXIMA = Collections.max(VALORES);
}

package tablero;

import java.util.Arrays;
import java.util.List;

public final class Altura {
    public static final int TIERRA = 1;
    public static final int AIRE = 2;

    private Altura() {
        // Utility class
    }

    public static final List<Integer> VALORES = Arrays.asList(
            TIERRA,
            AIRE
    );
}

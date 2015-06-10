package tablero;

import java.util.HashSet;
import java.util.Set;

// Utility class
public final class CoordenadaUtil {

    private CoordenadaUtil() {
    }

    public static Set<Coordenada> areaDeCoordenadas(Coordenada puntoMedio,
                                                    int rango) {
        Set<Coordenada> set = new HashSet<Coordenada>();

        if (rango >= 0) {
            set.add(puntoMedio);

            agregarRecursivamente(set, puntoMedio, rango);
        }

        return set;
    }

    private static void agregarRecursivamente(Set<Coordenada> set,
                                              Coordenada puntoMedio,
                                              int rango) {
        if (rango > 0) {
            for (Direccion direccion : Direccion.values()) {
                Coordenada nuevaCoordenada =
                        puntoMedio.dameCordenadaHacia(direccion);

                if (set.add(nuevaCoordenada)) {
                    agregarRecursivamente(set, nuevaCoordenada, rango - 1);
                }
            }
        }
    }
}

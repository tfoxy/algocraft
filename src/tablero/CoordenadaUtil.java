package tablero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Utility class
public final class CoordenadaUtil {

    private CoordenadaUtil() {
    }

    public static Set<Coordenada> areaDeCoordenadas(Coordenada puntoMedio,
                                                    int rango) {
        Set<Coordenada> set = new HashSet<>();

        if (rango >= 0) {
            set.add(puntoMedio);

            recorrerEnAnchura(set, puntoMedio, rango);
        }

        return set;
    }

    private static void recorrerEnAnchura(Set<Coordenada> set,
                                          Coordenada puntoMedio,
                                          int rango) {
        ArrayList<Coordenada> coordenadas = new ArrayList<>();
        coordenadas.add(puntoMedio);

        while (rango > 0) {
            final ArrayList<Coordenada> siguientes = new ArrayList<>();

            for (Coordenada coordenada : coordenadas) {
                for (Direccion direccion : Direccion.values()) {
                    Coordenada nuevaCoordenada =
                            coordenada.dameCordenadaHacia(direccion);

                    if (set.add(nuevaCoordenada)) {
                        siguientes.add(nuevaCoordenada);
                    }
                }
            }

            rango -= 1;

            coordenadas = siguientes;
        }
    }

    // TODO public static List<Ficha> getFichasEnArea(Tablero mapa, Coordenada puntoMedio, int rango)


}

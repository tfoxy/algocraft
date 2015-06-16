package tablero;

import error.PosicionFueraDeLimiteException;
import ficha.Ficha;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


    public static List<Ficha> fichasEnArea(Tablero mapa, Coordenada puntoMedio, int rango) {
        final Set<Coordenada> coordenadas = areaDeCoordenadas(puntoMedio, rango);
        final List<Ficha> fichas = new ArrayList<>(coordenadas.size());

        for (Coordenada coordenada: coordenadas) {
            for (int altura: Altura.VALORES) {
                Coordenada3d coord3d = new Coordenada3d(coordenada, altura);

                try {
                    Ficha ficha = mapa.getFicha(coord3d);
                    fichas.add(ficha);
                } catch (PosicionFueraDeLimiteException e) {
                    // noop
                }
            }
        }

        return fichas;
    }


}

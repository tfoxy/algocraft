package tablero;

import com.google.common.base.Preconditions;
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
                                                    int radio) {
        Preconditions.checkNotNull(puntoMedio, "Coordenada no debe ser nula");

        Set<Coordenada> set = new HashSet<>();

        if (radio >= 0) {
            set.add(puntoMedio);
            recorrerEnAnchura(set, puntoMedio, radio);
        }

        return set;
    }


    private static void recorrerEnAnchura(Set<Coordenada> set,
                                          Coordenada puntoMedio,
                                          int radio) {
        ArrayList<Coordenada> coordenadas = new ArrayList<>();
        coordenadas.add(puntoMedio);

        while (radio > 0) {
            final ArrayList<Coordenada> siguientes = new ArrayList<>();

            for (Coordenada coordenada : coordenadas) {
                for (Direccion direccion : Direccion.values()) {
                    Coordenada nuevaCoordenada =
                            coordenada.dameCoordenadaHacia(direccion);

                    if (set.add(nuevaCoordenada)) {
                        siguientes.add(nuevaCoordenada);
                    }
                }
            }

            radio -= 1;

            coordenadas = siguientes;
        }
    }

    public static List<Ficha> fichasEnArea(Ficha ficha, int radio) {
        return fichasEnArea(ficha.tablero(), ficha.coordenada(), radio);
    }

    public static List<Ficha> fichasEnArea(ITablero mapa, Coordenada puntoMedio, int radio) {
        final Set<Coordenada> coordenadas = areaDeCoordenadas(puntoMedio, radio);
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

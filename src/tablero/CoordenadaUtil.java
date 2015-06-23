package tablero;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import error.PosicionFueraDeLimiteException;
import ficha.Ficha;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

// Utility class
public final class CoordenadaUtil {

    private CoordenadaUtil() {
        // noop
    }


    private static class TableroPredicate implements Predicate<Coordenada> {
        private final ITablero tablero;

        public TableroPredicate(ITablero tablero) {
            this.tablero = tablero;
        }

        @Override
        public boolean apply(Coordenada coord) {
            try {
                tablero.verificarEnArea(coord);
                return true;
            } catch (PosicionFueraDeLimiteException e) {
                return false;
            }
        }
    }


    public static Set<Coordenada> coordenadasEnArea(Coordenada puntoMedio,
                                                    int radio) {
        Objects.requireNonNull(puntoMedio, "Coordenada no debe ser nula");

        Set<Coordenada> set = new HashSet<>();

        if (radio >= 0) {
            set.add(puntoMedio);
            recorrerEnAnchura(set, puntoMedio, radio,
                    Predicates.<Coordenada>alwaysTrue());
        }

        return set;
    }


    private static void recorrerEnAnchura(Set<Coordenada> set,
                                          Coordenada puntoMedio,
                                          int radio,
                                          Predicate<Coordenada> predicate) {
        ArrayList<Coordenada> coordenadas = new ArrayList<>();
        coordenadas.add(puntoMedio);

        while (radio > 0) {
            final ArrayList<Coordenada> siguientes = new ArrayList<>();

            for (Coordenada coordenada: coordenadas) {
                for (Direccion direccion: Direccion.values()) {
                    Coordenada nueva =
                            coordenada.dameCoordenadaHacia(direccion);

                    if (predicate.apply(nueva)
                            && set.add(nueva)) {
                        siguientes.add(nueva);
                    }
                }
            }

            radio -= 1;

            coordenadas = siguientes;
        }
    }

    public static Set<Coordenada> coordenadasEnArea(Ficha ficha, int radio) {
        Objects.requireNonNull(ficha, "Ficha no debe ser nula");

        final Coordenada puntoMedio = ficha.coordenada().proyeccion();
        final Set<Coordenada> set = new HashSet<>();
        Predicate<Coordenada> verificar = new TableroPredicate(ficha.tablero());

        if (radio >= 0) {
            set.add(puntoMedio);
            recorrerEnAnchura(set, puntoMedio, radio, verificar);
        }

        return set;
    }

    public static List<Ficha> fichasEnArea(Ficha ficha, int radio) {
        return fichasEnArea(ficha.tablero(), ficha.coordenada().proyeccion(), radio);
    }

    public static List<Ficha> fichasEnArea(ITablero mapa, Coordenada puntoMedio, int radio) {
        final Set<Coordenada> coordenadas = coordenadasEnArea(puntoMedio, radio);
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

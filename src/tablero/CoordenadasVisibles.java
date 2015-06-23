package tablero;

import java.util.HashSet;
import java.util.Set;

public class CoordenadasVisibles {

    private Set<Coordenada> coordenadasVisibles = new HashSet<>();

    public void verDesdeEstePunto(Coordenada coordenada, int vision) {
        coordenadasVisibles = CoordenadaUtil.coordenadasEnArea(coordenada, vision);
    }

    public boolean puedeVer(Coordenada coordenada) {
        return coordenadasVisibles.contains(coordenada);
    }


}

package tablero;

import java.util.HashSet;
import java.util.Set;


public class CasillasVisibles {

    private Set<Casilla> casillasVisibles = new HashSet<>();

    private void agregar(Casilla casilla) {
        casillasVisibles.add(casilla);
    }

    public void verDesdeEstePunto(Casilla casilla, int vision) {
        verRecursivamente(casilla, vision);
    }

    private void verRecursivamente(Casilla casilla, int vision) {
        agregar(casilla);

        if (vision > 0) {
            int siguienteVision = vision - 1;
            verRecursivamente(casilla.get(Direccion.ARRIBA), siguienteVision);
            verRecursivamente(casilla.get(Direccion.ABAJO), siguienteVision);
            verRecursivamente(casilla.get(Direccion.DERECHA), siguienteVision);
            verRecursivamente(casilla.get(Direccion.IZQUIERDA), siguienteVision);
        }
    }

    public boolean puedeVer(Casilla casilla) {
        return casillasVisibles.contains(casilla);
    }

}

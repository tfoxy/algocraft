package tablero;

import java.util.HashSet;
import java.util.Set;

public class CoordenadasVisibles {

    private Set<Coordenada> coordenadaVisibles = new HashSet<>();
    
    private void agregar(Coordenada coordenada) {
    	coordenadaVisibles.add(coordenada);
    }

    public void verDesdeEstePunto(Coordenada coordenada, int vision) {
        verRecursivamente(coordenada, vision);
    }

    private void verRecursivamente(Coordenada coordenada, int vision) {
        agregar(coordenada);

        if (vision > 0) {
            int siguienteVision = vision - 1;
            verRecursivamente(coordenada.dameCordenadaHacia(Direccion.ARRIBA), siguienteVision);
            verRecursivamente(coordenada.dameCordenadaHacia(Direccion.ABAJO), siguienteVision);
            verRecursivamente(coordenada.dameCordenadaHacia(Direccion.DERECHA), siguienteVision);
            verRecursivamente(coordenada.dameCordenadaHacia(Direccion.IZQUIERDA), siguienteVision);
        }
    }

    public boolean puedeVer(Coordenada coordenada) {
        return coordenadaVisibles.contains(coordenada);
    }

	
}

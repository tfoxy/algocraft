package ficha;


import juego.Recursos;

public class Asimilador extends EdifcioDeRecusosTerrestre {

    public Recursos extraer() {
        int cantidadExtraida = fuenteDeRecursos.extraer(10);

        return new Recursos(0, cantidadExtraida);
    };
}

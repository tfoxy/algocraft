package ficha;

import juego.Juego;
import juego.Recursos;

public abstract class FuenteDeRecurso extends FichaTerrestre {

    protected int cantidadDeRecursos;

    public FuenteDeRecurso(int cantidadDeRecursos) {
        this.cantidadDeRecursos = cantidadDeRecursos;

        this.estoyVacio = false;
        this.coste = new Recursos(0, 0, 0);
    }

    public int cantidadDeRecursos() {
        return cantidadDeRecursos;
    }

    public int extraer(int cantidad) {
        int cantidadOriginal = cantidadDeRecursos;

        cantidadDeRecursos = Math.max(0, cantidadDeRecursos - cantidad);

        return cantidadOriginal - cantidadDeRecursos;
    }

    @Override
    public abstract Recursos recursosVirgenes();

}

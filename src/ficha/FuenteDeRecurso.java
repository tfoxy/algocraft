package ficha;

import juego.Juego;

public abstract class FuenteDeRecurso extends FichaTerrestre {

    private static final int CANTIDAD_DE_RECURSOS_POR_DEFECTO = 1500;

    private int cantidadDeRecursos;

    public FuenteDeRecurso(Juego juego) {
        this(juego, CANTIDAD_DE_RECURSOS_POR_DEFECTO);
    }


    public FuenteDeRecurso(Juego juego, int cantidadDeRecursos) {
        super(juego);

        if (cantidadDeRecursos <= 0) {
            throw new IllegalArgumentException();
        }

        this.cantidadDeRecursos = cantidadDeRecursos;
    }


    public int getCantidadDeRecursos() {
        return cantidadDeRecursos;
    }


    public int extraer(int cantidad) {
        int cantidadOriginal = cantidadDeRecursos;

        cantidadDeRecursos = Math.max(0, cantidadDeRecursos - cantidad);

        return cantidadOriginal - cantidadDeRecursos;
    }

}

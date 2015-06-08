package ficha;

import error.ArgumentoNoPuedeSerNegativoException;
import jugador.Gaia;

public abstract class FuenteDeRecurso extends FichaTerrestre {

    private int cantidadDeRecursos;


    public FuenteDeRecurso(int cantidadDeRecursos) {
        super(new Gaia());

        if (cantidadDeRecursos < 0) {
            throw new ArgumentoNoPuedeSerNegativoException();
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

package ficha;

import juego.Recursos;
import juego.RecursosDeJugador;

public abstract class FuenteDeRecurso extends FichaTerrestre {

    protected RecursosDeJugador cantidadDeRecursos;

    public FuenteDeRecurso(int cristal, int gas) {
        cantidadDeRecursos = new RecursosDeJugador(cristal, gas);

        this.tipoDeFicha.add(TipoDeFicha.NATURAL);
        this.tipoDeFicha.add(TipoDeFicha.RECURSO);
    }

    public int cantidadDeGas() {
        return cantidadDeRecursos.gas();
    }

    public int cantidadDeCristal() {
        return cantidadDeRecursos.mineral();
    }

    public void extraer(Recursos cantidad) {
        cantidadDeRecursos.gastar(cantidad);
    }

    public boolean haySuficienteRecursos(Recursos coste) {
        return cantidadDeRecursos.haySuficienteRecursos(coste);
    }

    @Override
    public RecursosDeJugador recursosVirgenes() {
        return cantidadDeRecursos;
    }

}

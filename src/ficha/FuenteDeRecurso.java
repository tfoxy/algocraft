package ficha;

import error.FichaSobreOtraFichaException;
import error.NoSePuedeCrearFicha;
import juego.Juego;
import juego.Recursos;
import juego.RecursosDeJugador;

public abstract class FuenteDeRecurso extends FichaTerrestre {

    protected RecursosDeJugador cantidadDeRecursos;

    public FuenteDeRecurso(int cristal, int gas) {
        cantidadDeRecursos = new RecursosDeJugador(cristal,gas);

        this.estoyVacio = false;
        this.coste = new Recursos(0, 0, 0);
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
    
    public boolean sePuedeCrear() throws NoSePuedeCrearFicha { //es esto o dos cath. Igual hay que revisar mas adelante que pasa si tenes la cantidad exatcta de recurssos.
        if (!(tablero.hayEspacio(coordenada2))) {
            throw new FichaSobreOtraFichaException();
        }
        return true;
    }

}

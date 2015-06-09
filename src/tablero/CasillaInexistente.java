package tablero;

import ficha.FichaAerea;
import ficha.FichaTerrestre;

public class CasillaInexistente implements Casilla {

    // TODO si CasillaInexistente no se utiliza en el proyecto en un futuro, eliminarla

    // Se debería usar FichaFueraDeTablero
    private FichaTerrestre fichaTerreste = new FichaTerrestre();
    private FichaAerea fichaAerea = new FichaAerea();

    @Override
    public FichaTerrestre getFichaTerrestre() {
        return fichaTerreste;
    }

    @Override
    public FichaAerea getFichaAerea() {
        return fichaAerea;
    }

    @Override
    public void insertar(FichaTerrestre ficha) {
        // No hacer nada
    }

    @Override
    public void insertar(FichaAerea ficha) {
        // No hacer nada
    }

    @Override
    public void eliminarFichaTerrestre() {
        // No hacer nada
    }

    @Override
    public void eliminarFichaAerea() {
        // No hacer nada
    }

    @Override
    public Casilla get(Movimiento movimiento) {
        return this;
    }
}

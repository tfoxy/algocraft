package tablero;

import ficha.FichaAerea;
import ficha.FichaFueraDeTablero;
import ficha.FichaTerrestre;

public class CasillaInexistente implements Casilla {

    private FichaTerrestre fichaTerreste = new FichaFueraDeTablero();
    private FichaAerea fichaAerea = new FichaFueraDeTablero();

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

package tablero;

import ficha.FichaAerea;
import ficha.FichaAire;
import ficha.FichaTerrestre;
import ficha.FichaTierra;

public class CasillaInexistente implements Casilla {

    private FichaTerrestre fichaTerreste = new FichaTierra();
    private FichaAerea fichaAerea = new FichaAire();

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

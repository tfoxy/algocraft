package tablero;

import error.FichaSobreOtraFichaException;
import ficha.FichaTerrestre;
import ficha.FichaAerea;
import ficha.FichaTierra;
import ficha.FichaAire;

import java.util.EnumMap;

public class CasillaDeTablero implements Casilla {

    private FichaTerrestre fichaTerreste;
    private FichaAerea fichaAerea;

    private EnumMap<Movimiento, Casilla> casillasAdyacentes =
            new EnumMap<>(Movimiento.class);

    public CasillaDeTablero() {
        fichaTerreste = new FichaTierra();
        fichaAerea = new FichaAire();

        for (Movimiento movimiento: Movimiento.values()) {
            insertarCasillaAdyacente(movimiento, new CasillaInexistente());
        }
    }

    void insertarCasillaAdyacente(Movimiento movimiento, Casilla casilla) {
        casillasAdyacentes.put(movimiento, casilla);
    }

    public boolean estaVacia() {
        return (fichaTerreste.estaVacia() && fichaAerea.estaVacia());
    }

    public boolean hayEspacioTerreste() {
        return fichaTerreste.estaVacia();
    }

	public boolean hayEspacioArreo() { //new 6
		return fichaAerea.estaVacia();
	}

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
        if (!fichaTerreste.estaVacia()) {
            throw new FichaSobreOtraFichaException();
        } else {
            fichaTerreste = ficha;
        }
    }

    @Override
    public void insertar(FichaAerea ficha) {
        if (!fichaAerea.estaVacia()) {
            throw new FichaSobreOtraFichaException();
        } else {
            fichaAerea = ficha;
        }
    }

    @Override
    public void eliminarFichaTerrestre() {
        fichaTerreste = new FichaTierra();
    }

    @Override
    public void eliminarFichaAerea() {
        fichaAerea = new FichaAire();
    }

    @Override
    public Casilla get(Movimiento movimiento) {
        return casillasAdyacentes.get(movimiento);
    }


}

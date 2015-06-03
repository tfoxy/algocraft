package Tablero;

import Errores.FichaSobreOtraFichaException;
import Ficha.FichaTerrestre;
import Ficha.FichaAerea;
import Ficha.FichaVacia;

public class Casillero implements Casilla {

	private FichaTerrestre fichaTerreste;
	private FichaAerea fichaAerea;
	
	public Casillero() {
		fichaTerreste = new FichaVacia();
		fichaAerea = new FichaVacia();
	}
	
	public boolean EstasVacia() {
		return (fichaTerreste.EstasVacia() && fichaAerea.EstasVacia());
	}

	public boolean HayespacioTerreste() {
		return fichaTerreste.EstasVacia();
	}

	public void NewFichaTerreste(FichaTerrestre ficha) {
		fichaTerreste = ficha;
		
	}

	public void MuereFichaTerreste() {
		fichaTerreste = new FichaVacia();
	}

	public FichaTerrestre FichaTerreste() {
		return fichaTerreste;
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
        if (!fichaTerreste.EstasVacia()) {
            throw new FichaSobreOtraFichaException();
        }
        else {
            fichaTerreste = ficha;
        }
    }

    @Override
    public void insertar(FichaAerea ficha) {
        if (!fichaAerea.EstasVacia()) {
            throw new FichaSobreOtraFichaException();
        }
        else {
            fichaAerea = ficha;
        }
    }

    @Override
    public void eliminarFichaTerrestre() {
        fichaTerreste = new FichaVacia();
    }

    @Override
    public void eliminarFichaAerea() {
        fichaAerea = new FichaVacia();
    }


}

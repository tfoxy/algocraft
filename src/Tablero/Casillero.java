package Tablero;

import Errores.FichaEnTerrenoIncorrectoException;
import Errores.FichaSobreOtraFichaException;
import Ficha.FichaTerrestre;
import Ficha.FichaAerea;
import Ficha.FichaVacia;

public class Casillero implements Casilla {

	private Terreno terreno;
	private FichaTerrestre fichaTerreste;
	private FichaAerea fichaAerea;
	
	public Casillero() {
		terreno = Terreno.TERRESTRE;
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
    public Terreno getTerreno() {
        return terreno;
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
        else if (terreno == Terreno.ESPACIAL) {
            throw new FichaEnTerrenoIncorrectoException();
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

    @Override
    public void modificar(Terreno terreno) {
        if (!fichaTerreste.EstasVacia()) {
            throw new FichaEnTerrenoIncorrectoException();
        }
        else {
            this.terreno = terreno;
        }
    }


}

package Tablero;

import Errores.FichaEnTerrenoIncorrectoException;
import Errores.FichaSobreOtraFichaException;
import Ficha.Ficha;
import Ficha.FichaAerea;
import Ficha.FichaVacia;

public class Casillero implements Casilla {

	private Terreno terreno;
	private Ficha FichaTerreste;
	private FichaAerea FichaVoladora;
	
	public Casillero() {
		terreno = Terreno.TERRESTRE;
		FichaTerreste = new FichaVacia();
		FichaVoladora = new FichaVacia();
	}
	
	public boolean EstasVacia() {
		return (FichaTerreste.EstasVacia() && FichaVoladora.EstasVacia());
	}

	public boolean HayespacioTerreste() {
		return FichaTerreste.EstasVacia();
	}

	public void NewFichaTerreste(Ficha ficha) {
		FichaTerreste = ficha;
		
	}

	public void MuereFichaTerreste() {
		FichaTerreste = new FichaVacia();	
	}

	public String TienesUnRecurso() {
		return FichaTerreste.HeresUnRecurso();
	}

	public Ficha FichaTerreste() {
		return 	FichaTerreste;
	}


    @Override
    public Terreno getTerreno() {
        return terreno;
    }

    @Override
    public Ficha getFichaTerrestre() {
        return FichaTerreste;
    }

    @Override
    public FichaAerea getFichaAerea() {
        return FichaVoladora;
    }

    @Override
    public void insertar(Ficha ficha) {
        if (!FichaTerreste.EstasVacia()) {
            throw new FichaSobreOtraFichaException();
        }
        else if (terreno == Terreno.ESPACIAL) {
            throw new FichaEnTerrenoIncorrectoException();
        }
        else {
            FichaTerreste = ficha;
        }
    }

    @Override
    public void insertar(FichaAerea ficha) {
        if (!FichaVoladora.EstasVacia()) {
            throw new FichaSobreOtraFichaException();
        }
        else {
            FichaVoladora = ficha;
        }
    }

    @Override
    public void eliminarFichaTerrestre() {
        FichaTerreste = new FichaVacia();
    }

    @Override
    public void eliminarFichaAerea() {
        FichaVoladora = new FichaVacia();
    }

    @Override
    public void modificar(Terreno terreno) {
        if (!FichaTerreste.EstasVacia()) {
            throw new FichaEnTerrenoIncorrectoException();
        }
        else {
            this.terreno = terreno;
        }
    }


}

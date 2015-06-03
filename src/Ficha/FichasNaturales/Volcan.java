package Ficha.FichasNaturales;

import Errores.NoSePuedeCrear;
import Ficha.Ficha;
import Tablero.Cordenada;
import Tablero.Tablero;

public class Volcan implements Ficha {

    private static final int GAS_POR_DEFECTO = 5000;

    public int Gaces;//malpensados

    public Volcan() {
        Gaces = GAS_POR_DEFECTO;
    }

	public Volcan (int gas) {
		Gaces = gas;
	}
	
	public Volcan (int gas, Cordenada lugar,Tablero mapa) {
		Gaces= gas;
		if (!(mapa.HayEspacioTerreste (lugar))){
			throw new NoSePuedeCrear("Espacio Ocupado");
		}
		mapa.NewFichaTerreste(lugar, this);
	}
	
	@Override
	public boolean EstasVacia() {
		return false;
	}

	@Override
	public void PasarTurno() {
		
	}

	@Override
	public void Muerete() {
	}

}

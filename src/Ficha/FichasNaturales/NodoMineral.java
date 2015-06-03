package Ficha.FichasNaturales;

import Errores.NoSePuedeCrear;
import Ficha.Ficha;
import Ficha.FuenteDeRecurso;
import Tablero.Cordenada;
import Tablero.Tablero;

public class NodoMineral implements FuenteDeRecurso {

    public static int MINERALES_POR_DEFECTO = 1500;

	public int Minerales;

    public NodoMineral() {
        Minerales = MINERALES_POR_DEFECTO;
    }

    public NodoMineral(int mineral) {
        Minerales= mineral;
    }
	
	public NodoMineral (int mineral,Tablero mapa, Cordenada lugar) {
		Minerales= mineral;
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

	@Override
	public String HeresUnRecurso() {
		return "NodoMinerales";
	}

}

package Ficha.FichasNaturales;

import Errores.ArgumentoNoPuedeSerNegativoException;
import Errores.NoSePuedeCrear;
import Ficha.FuenteDeRecurso;
import Tablero.Cordenada;
import Tablero.Tablero;

public class NodoMineral extends FuenteDeRecurso {

    private static int MINERALES_POR_DEFECTO = 1500;

    public NodoMineral() {
		super(MINERALES_POR_DEFECTO);
    }

    public NodoMineral(int mineral) throws ArgumentoNoPuedeSerNegativoException {
		super(mineral);
    }
	
	public NodoMineral(int mineral, Tablero mapa, Cordenada lugar) throws ArgumentoNoPuedeSerNegativoException {
		this(mineral);
		if (!(mapa.HayEspacioTerreste (lugar))){
			throw new NoSePuedeCrear("Espacio Ocupado");
		}
		mapa.NewFichaTerreste(lugar, this);
	}

	@Override
	public void PasarTurno() {
		// TODO implementar
	}

	@Override
	public void Muerete() {
		// TODO implementar
	}

}

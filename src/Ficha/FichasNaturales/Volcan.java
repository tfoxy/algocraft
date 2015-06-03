package Ficha.FichasNaturales;

import Errores.NoSePuedeCrear;
import Errores.ArgumentoNoPuedeSerNegativoException;
import Ficha.FuenteDeRecurso;
import Tablero.Cordenada;
import Tablero.Tablero;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        super(GAS_POR_DEFECTO);
    }

	public Volcan (int gas) throws ArgumentoNoPuedeSerNegativoException {
        super(gas);
	}
	
	public Volcan (int gas, Cordenada lugar, Tablero mapa) throws ArgumentoNoPuedeSerNegativoException {
		this(gas);
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

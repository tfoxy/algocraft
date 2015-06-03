package Ficha;

import Jugador.Recursos;
import Jugador.TablaJugador;


public class NexoMineral extends Edificio implements FichaTerrestre {
	
	private Recursos Coste;
	private int TurnosParaCrear;

	public NexoMineral( TablaJugador propetario) {
		super(propetario);
		Coste = new Recursos (50, 0);
		TurnosParaCrear = 4;
	}

	@Override
	public void PasarTurno() {
		Propetario.AgregarRecursosLineales(10,0);
	}

	@Override
	public void Muerete() {
		// TODO Auto-generated method stub
		
	}

}

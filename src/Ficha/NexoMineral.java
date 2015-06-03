package Ficha;

import Jugador.Recursos;
import Jugador.TablaJugador;


public class NexoMineral extends Edificio {
	
	private Recursos Coste;
	private int TurnosParaCrear;
	private TablaJugador Propetario;

	public NexoMineral( TablaJugador propetario) {
		Propetario = propetario;
		Coste = new Recursos (50, 0);
		TurnosParaCrear = 4;
	}
	
	public void PasarTurno() {
		Propetario.AgregarRecursosLineales(10,0);
	}

	@Override
	public void Muerete() {
		// TODO Auto-generated method stub
		
	}

}

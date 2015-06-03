package Ficha;

public class FichaVacia implements FichaTerrestre, FichaAerea {

	@Override
	public boolean EstasVacia() {
		return true;
	}

	@Override
	public void PasarTurno() {
		
	}

	@Override
	public void Muerete() {
		// TODO Auto-generated method stub
	}

}

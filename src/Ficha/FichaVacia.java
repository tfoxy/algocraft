package Ficha;

public class FichaVacia implements Ficha, FichaAerea {

	public boolean EstasVacia() {
		return true;
	}

	public void PasarTurno() {
		
	}

	@Override
	public void Muerete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String HeresUnRecurso() {
		// TODO Auto-generated method stub
		return "no";
	}

}

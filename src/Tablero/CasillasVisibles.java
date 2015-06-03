package Tablero;

import java.util.ArrayList;


public class CasillasVisibles {
	
	private ArrayList<Cordenada> CasillasVisibles = new ArrayList<>();
	
	private void AgregarCordenadna(Cordenada cordenadaNueva){
		if (!CasillasVisibles.contains(cordenadaNueva))
			CasillasVisibles.add(cordenadaNueva);
	}
	
	public void VerDesdeEstePunto(Cordenada observador, int Vision){
		VerDesdeEstePuntoRecursivo(observador, Vision);
	}
	
	private void VerDesdeEstePuntoRecursivo(Cordenada observador, int Vision){
		if (Vision > -1){   //la condicionPuedeVariar 
		AgregarCordenadna (observador);
		VerDesdeEstePuntoRecursivo(observador.arriba(), Vision-1);
		VerDesdeEstePuntoRecursivo(observador.abajo(), Vision-1);
		VerDesdeEstePuntoRecursivo(observador.derecha(), Vision-1);
		VerDesdeEstePuntoRecursivo(observador.izquierda(), Vision-1);
		}
	}
	
	public boolean PuedeVerEsto(Cordenada esto){
		return CasillasVisibles.contains(esto);
	}
	
}

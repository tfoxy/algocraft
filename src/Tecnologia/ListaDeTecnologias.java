package Tecnologia;

import java.util.ArrayList;



public class ListaDeTecnologias {
	private ArrayList<String> MisTecnologias = new ArrayList<>();
	
	public void Agregar(String Entra){
		MisTecnologias.add(Entra);
	}
	
	public void Quitar(String Sale){
		MisTecnologias.remove(Sale);
	}
	
	public boolean  TienesEsto(String esto){
		return MisTecnologias.contains(esto);
	}

	public boolean ContengoEstasTecnologias(ListaDeTecnologias tecnologias) {

		return MisTecnologias.containsAll(tecnologias.MisTecnologias);
	}
	
}

package Jugador;

import java.util.ArrayList;

import Ficha.Ficha;
import Tecnologia.ListaDeTecnologias;


public class TablaJugador {
	
	ArrayList<Ficha> MisFichas = new ArrayList<>();
	public String Nombre;
	public String Raza;
	private Recursos recursos;
	private ListaDeTecnologias MisTecnologias;
	
	//esteEsObselete
	public TablaJugador( String nombre, String raza){
			Nombre = nombre;
			Raza = raza;
			recursos = new Recursos(0,0,0);
	}
	
	
	//constuctorCompleto
	public TablaJugador( String nombre, String raza, int cristalInicial, int gasInicial){
		Nombre = nombre;
		Raza = raza;
		recursos = new Recursos(cristalInicial,gasInicial,0);
		MisTecnologias = new ListaDeTecnologias();
		MisTecnologias.Agregar(raza);
}

	//get para Text//
	public int CantidadCriztal() {
		return recursos.CantidadCrital();
	}
	public int CantidadGaz() {
		return recursos.CantidadGaz();
	}
	public int PoblcacionPosible() {

		return recursos.PoblacionPosible();
	}
	public int PoblcacionActual() {
		return recursos.PoblcacionActual();
	}	

	
	//recursos
	public void AgregarRecursosLineales(int cristal, int gas) {
		recursos.AgregarRecursosLineales(cristal, gas);
		
	}
	public void GastaRecursos(Recursos coste) {
		recursos.GastaRecursos(coste);
	}
	public void AgregarPoblacionTotal(int AumentoDePoblacion) {
		recursos.AgregarPoblacionTotal(AumentoDePoblacion);
	}
	public void PerderPoblacionTotal(int desensoDePoblacion) {
		recursos.PerderPoblacionTotal(desensoDePoblacion);
		
	}
	public void PerderPoblacionActual(int desensoDePoblacion) {
		recursos.PerderPoblacionActual(desensoDePoblacion);
	}

	//Ficha
	public void NewFicha(Ficha nuevaFicha) {
		MisFichas.add(nuevaFicha);
		
	}
	public void PerderFicha(Ficha fichaPerdida) {
		MisFichas.remove(fichaPerdida);
		
	}

	
	//otros
	

	public void PasarTurno() {
		for(Ficha object: MisFichas){
			object.PasarTurno();
		}
		
	}


	public boolean TengoSuficientesRecursos(Recursos coste) {
		return recursos.TengoSuficientesRecursos(coste);
	}

	//tecnologia
	public void AgregarTecnologia(String tecnologia) {
		MisTecnologias.Agregar(tecnologia);
	}
	
	public boolean TienesLasTecnologias( ListaDeTecnologias lasTecnologiasNecesarias) {
		return MisTecnologias.ContengoEstasTecnologias(lasTecnologiasNecesarias) ;
	}


	
	
	
}

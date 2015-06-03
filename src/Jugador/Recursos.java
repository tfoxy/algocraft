package Jugador;

public class Recursos {
	
	private int Cristal = 0;
	private int Gas = 0;
	private int PoblacionActual = 0;
	private int PoblacionPosibleUsable = 0;
	private int PoblacionPosibleTotal = 0;
	
	
	//constructores//
	public Recursos( int cristal, int gas) {
		Cristal = cristal;
		Gas = gas;
	}
	
	public Recursos( int cristal, int gas, int poblacion) {
		Cristal = cristal;
		Gas = gas;
		PoblacionActual = poblacion;
	}

	//Gets Usados En Text//
	
	public int CantidadCrital() {
		return Cristal;
	}
	public int CantidadGaz() {
		return Gas;
	}
	public int PoblacionPosible() {
		return PoblacionPosibleUsable;
	}
	
	//este se usa de verdad
	public int PoblcacionActual() {
		return PoblacionActual;
	}

	
	//AlteraraRecrusos//
	public void AgregarRecursosLineales(int cristal, int gas) {
		Cristal = Cristal + cristal;
		Gas = Gas + gas;
		
	}
	
	public void AgregarPoblacionTotal(int aumentoDePoblacion) {
		PoblacionPosibleTotal = PoblacionPosibleTotal + aumentoDePoblacion;
		if (PoblacionPosibleTotal > 200){
			PoblacionPosibleUsable = 200;
		} 
		else{
			PoblacionPosibleUsable = PoblacionPosibleTotal;
		}
	}
	public void PerderPoblacionActual(int desensoDePoblacion) {
		PoblacionActual = PoblacionActual -desensoDePoblacion;
	}
	public void PerderPoblacionTotal(int decensoDePoblacion) {
		PoblacionPosibleTotal = PoblacionPosibleTotal - decensoDePoblacion;
		if (PoblacionPosibleTotal > 200){
			PoblacionPosibleUsable = 200;
		} 
		else{
			PoblacionPosibleUsable = PoblacionPosibleTotal;
		}
		
	}

	public void GastaRecursos(Recursos coste) {
		Gas = Gas - coste.Gas;
		Cristal = Cristal - coste.Cristal;
		PoblacionActual = PoblacionActual + coste.PoblacionActual;
	}

	
	//ComparaRecursos//
	public boolean TengoSuficientesRecursos(Recursos coste) {
		return ((Gas >= coste.Gas )&&(Cristal >= coste.Cristal)&& ((PoblacionPosibleUsable-PoblacionActual) >= coste.PoblacionActual));
	}



}

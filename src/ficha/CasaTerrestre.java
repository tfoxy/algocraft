package ficha;

public class CasaTerrestre extends EdificioTerrestre {

    public CasaTerrestre() {
        poblacionQueDa = 5;
    }

    public void muerete() {
    	super.muerete();
    	
        if (esToyConstruido) {
            propietario.perderPoblacionTotal(poblacionQueDa);
        }
    }
    
    
    public void pasarTurno() {
        turnosParaCrear = turnosParaCrear - 1; //cualqueier cosa que se pase de largo
        barras.pasarTurno();
        this.revisarEventos();
    }

    public void revisarEventos() {
    	if(turnosParaCrear == 0)	{
    		this.construir();
    	}
    }
    
    public void construir(){
  	   esToyConstruido = true;
  	   propietario.agregarPoblacionTotal(poblacionQueDa);
      }
}

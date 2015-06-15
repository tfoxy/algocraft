package ficha;

public class CasaTerrestre extends EdificioTerrestre {

    public CasaTerrestre() {
        poblacionQueDa = 5;
    }

    @Override
    public void muerete() {
    	super.muerete();
    	
        if (estoyConstruido) {
            propietario.perderPoblacionTotal(poblacionQueDa);
        }
    }
    
    
    @Override
    public void pasarTurno() {
        turnosParaCrear = turnosParaCrear - 1; //cualqueier cosa que se pase de largo
        barras.pasarTurno();
        this.revisarEventos();
    }

    @Override
    public void revisarEventos() {
    	if(turnosParaCrear == 0)	{
    		this.construir();
    	}
    }
    
    @Override
    public void construir(){
  	   estoyConstruido = true;
  	   propietario.agregarPoblacionTotal(poblacionQueDa);
      }
}

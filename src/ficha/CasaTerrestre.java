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
    
}

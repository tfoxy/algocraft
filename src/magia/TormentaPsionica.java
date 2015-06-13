package magia;

import ficha.FichaAerea;

public class TormentaPsionica extends FichaAerea {
	
	private int duracion;
	
	
	public TormentaPsionica(){
		duracion = 2;
    }
	
	
    public void pasarTurno() {
    	duracion = duracion - 1;
    	if (!tablero.hayEspacioArreo(coordenada)){
    	tablero.getFichaAerea(coordenada).serAtacado(100);
    	}
    	if (!tablero.hayEspacioTerreste(coordenada)){
        tablero.getFichaTerrestre(coordenada).serAtacado(100);
    	}
    	if (duracion == 0){
    		propietario.perder(this);
    	}
    }

}

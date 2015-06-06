package estrategia.ficha;

import tablero.Casilla;
import tablero.CasillaDeTablero;
import tablero.Coordenada;
import tablero.Movimiento;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import error.PosicionFueraDeLimiteException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaAire;
import ficha.FichaTerrestre;
import ficha.FichaTierra;

//la ide es que sea implementada por otras Extrategias.
public class EstrategiaMover {

	
	Tablero mapa;
	Coordenada ubicacion;
	Coordenada nuevaUbicacion;
	
	//terreste
	public boolean intentarMovimiento( FichaTerrestre ficha, Movimiento dirrecion){
		
		mapa = ficha.mapa;
		ubicacion = ficha.ubicacion;
		nuevaUbicacion = ubicacion.dameCordenadaAtu (dirrecion);
		
		if (this.sePuedeMover(ficha, dirrecion)){
			mapa.insertar(nuevaUbicacion, ficha);
			mapa.insertar(ubicacion, new FichaTierra());
			ficha.ubicacion = nuevaUbicacion;
		}
		return false;
	}
	
	private boolean sePuedeMover( FichaTerrestre ficha, Movimiento dirrecion){
		
		//si se quita el private hay que re inicializar
		try {
			mapa.getCasilla(ficha.ubicacion.dameCordenadaAtu (dirrecion));
		}
		catch (PosicionFueraDeLimiteException e) //cosa a verificar.
		{
			return false;
		}
			 
        if (mapa.hayEspacioTerreste(ubicacion.dameCordenadaAtu (dirrecion))) {
    		return false;
        }

        return true;
	}
	
	
	//areo
	public boolean intentarMovimiento( FichaAerea ficha, Movimiento dirrecion){
		
		mapa = ficha.mapa;
		ubicacion = ficha.ubicacion;
		nuevaUbicacion = ubicacion.dameCordenadaAtu (dirrecion);
		
		if (this.sePuedeMover(ficha, dirrecion)){
			mapa.insertar(nuevaUbicacion, ficha);
			mapa.insertar(ubicacion, new FichaAire());
			Ficha.ubicacion = nuevaUbicacion;
		}
		return false;
	}
	
	private boolean sePuedeMover( FichaAerea ficha, Movimiento dirrecion){
	
		//si se quita el private hay que re inicializar
		
		try {
			mapa.getCasilla(ficha.ubicacion.dameCordenadaAtu(dirrecion));
		}
		catch (PosicionFueraDeLimiteException e) //cosa a verificar.
		{
			return false;
		}
			 
        if (mapa.hayEspacioArreo(ubicacion.dameCordenadaAtu(dirrecion))) {
    		return false;
        }

        return true;
	}
	
	
}

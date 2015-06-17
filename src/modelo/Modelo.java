package modelo;

import tablero.Coordenada;
import tablero.Tablero;
import juego.Recursos;


public class Modelo extends java.util.Observable{
	
	public Recursos recursos;
	public Tablero tablero;
    private int modeloDatos[][];
    

	public void ActualizarObservadores()
	{
		setChanged();
		notifyObservers();		
	}


	//aca termine por ahora.
    public void inicializarModeloDato(Coordenada posicion) {
        modeloDatos[posicion.x][posicion.y] = 0;
        setChanged();
        
        this.notifyObservers();
    }

    public void setModeloDato(Coordenada posicion, int dato) {
        modeloDatos[posicion.x][posicion.y] = dato;
        setChanged();
        this.notifyObservers();
    }

    public int getModeloDato(Coordenada posicion) {
        return modeloDatos[posicion.x][posicion.y];
    }
	

}

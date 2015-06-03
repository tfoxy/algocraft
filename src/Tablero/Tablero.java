package Tablero;

import java.util.HashMap;
import java.util.Map;

import Errores.PosicionFueraDeLimiteException;
import Ficha.Ficha;

public class Tablero {


	private int longitudX;
	private int longitudY;
	public Map<Cordenada, Casillero> Tablero = new HashMap<>();

	public Tablero(int x, int y) {//esto deveria crear de 1 a X invlcusive//
		longitudX = x;
		longitudY = y;

		for (int i = 1; i < (x+1); i++){
			for (int j = 1; j < (y+1); j++){
				Tablero.put(new Cordenada(i,j), new Casillero());
			}
		}	
	}

	public boolean HayEspacioTerreste(Cordenada lugar) {
		
		return Tablero.get(lugar).HayespacioTerreste();
	}

	public void NewFichaTerreste(Cordenada lugar, Ficha ficha) {
		Casillero Lugar = Tablero.get(lugar);
		Lugar.NewFichaTerreste (ficha);
	}	
	
	public void MuereFichaTerreste(Cordenada lugar, Ficha ficha) {
		Casillero Lugar = Tablero.get(lugar);
		Lugar.MuereFichaTerreste();
	}
	
	public String HayUnaFuenteDeRecuros(Cordenada lugar) {
		Casillero Lugar = Tablero.get(lugar);
		return Lugar.TienesUnRecurso();
	}

	public Ficha DameLaFichaTerresteEn(Cordenada lugar) {
		Casillero Lugar = Tablero.get(lugar);
		return Lugar.FichaTerreste();
	}

	public Casilla getCasilla(Cordenada lugar) {
		Casilla casilla = Tablero.get(lugar);

        if (casilla == null) {
            throw new PosicionFueraDeLimiteException();
        }

        return casilla;
	}

	public void insertar(Cordenada lugar, Ficha ficha) {
		getCasilla(lugar).insertar(ficha);
	}

	public int getLongitudX() {
		return longitudX;
	}

	public int getLongitudY() {
		return longitudY;
	}
}

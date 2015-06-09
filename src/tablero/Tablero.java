package tablero;

import java.util.HashMap;
import java.util.Map;

import error.PosicionFueraDeLimiteException;
import ficha.FichaAerea;
import ficha.FichaTerrestre;

public class Tablero {


    private int longitudX;
    private int longitudY;
    private Map<Coordenada, CasillaDeTablero> casillas = new HashMap<>();


    public Tablero(int x, int y) {
        longitudX = x;
        longitudY = y;

        inicializarCasillas();
    }


    private void inicializarCasillas() {
        Coordenada coordenada;
        CasillaDeTablero casilla;

        for (int i = 0; i < longitudX; i++) {
            for (int j = 0; j < longitudY; j++) {
                coordenada = new Coordenada(i + 1, j + 1);
                casilla = new CasillaDeTablero();
                casillas.put(coordenada, casilla);
            }
        }
    }


    public boolean hayEspacioTerreste(Coordenada lugar) {
        return casillas.get(lugar).hayEspacioTerreste();
    }

    public boolean hayEspacioArreo(Coordenada lugar) { //new 6
        return casillas.get(lugar).hayEspacioArreo();
    }


    public CasillaDeTablero getCasilla(Coordenada lugar) {
        CasillaDeTablero casilla = casillas.get(lugar);

        if (casilla == null) {
            throw new PosicionFueraDeLimiteException();
        }

        return casilla;
    }


    public void insertar(Coordenada lugar, FichaTerrestre ficha) {
        getCasilla(lugar).insertar(ficha);
    }


    public void insertar(Coordenada lugar, FichaAerea ficha) {
        getCasilla(lugar).insertar(ficha);
    }


    public int getLongitudX() {
        return longitudX;
    }


    public int getLongitudY() {
        return longitudY;
    }
}

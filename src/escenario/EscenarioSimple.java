package escenario;


import ficha.Ficha;
import ficha.natural.recurso.NodoMineral;
import ficha.natural.recurso.Volcan;
import gui.modelo.TableroObservable;
import juego.Gaia;
import juego.Juego;
import juego.Jugador;
import tablero.Coordenada;
import tablero.ITablero;
import tablero.Tablero;

public class EscenarioSimple {

    private static final int W = 29;
    private static final int H = 15;
    private static final Coordenada PUNTO_MEDIO = new Coordenada((W + 1) / 2, (H + 1) / 2);

    private ITablero mapa;
    private Gaia gaia;
    private Jugador j1;
    private Jugador j2;

    public EscenarioSimple() {
        mapa = new Tablero(W, H);
    }

    private static Coordenada c(int x, int y) {
        return new Coordenada(x, y);
    }

    private static int simetrica(int x, int medio) {
        return 2 * medio - x;
    }

    private static Coordenada simetrica(Coordenada coord) {
        int x = simetrica(coord.getX(), PUNTO_MEDIO.getX());
        int y = simetrica(coord.getY(), PUNTO_MEDIO.getY());

        return new Coordenada(x, y);
    }

    private void nuevoVolcan(Coordenada coord) {
        Volcan volcan = new Volcan();
        volcan.setBasico(gaia, mapa, coord);
        volcan.ponerEnJuego();
    }

    private void nuevoMineral(Coordenada coord) {
        NodoMineral mineral = new NodoMineral();
        mineral.setBasico(gaia, mapa, coord);
        mineral.ponerEnJuego();
    }

    private void nuevaUnidadBasica(Jugador jugador, Coordenada coord) {
        Ficha unidad = jugador.raza().nuevaUnidadBasica();
        unidad.setBasico(jugador, mapa, coord);
        unidad.ponerEnJuego();
    }

    public Juego cargarEn(Juego.Builder builder) {
        builder.tablero(mapa);

        Juego juego = builder.build();

        gaia = juego.gaia();
        j1 = juego.jugadores().get(0);
        j2 = juego.jugadores().get(1);

        nuevoVolcan(c(1, 8));
        nuevoVolcan(simetrica(c(1, 8)));

        nuevoMineral(c(1, 7));
        nuevoMineral(simetrica(c(1, 7)));

        nuevoMineral(c(1, 9));
        nuevoMineral(simetrica(c(1, 9)));

        nuevoVolcan(PUNTO_MEDIO);
        nuevoMineral(c(15, 7));
        nuevoMineral(c(15, 9));

        nuevaUnidadBasica(j1, c(2, 8));
        nuevaUnidadBasica(j2, simetrica(c(2, 8)));

        return juego;
    }

}

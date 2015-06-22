package escenario;


import ficha.Ficha;
import ficha.magia.TormentaPsionicaFicha;
import ficha.natural.recurso.NodoMineral;
import ficha.natural.recurso.Volcan;
import ficha.natural.terreno.TerrenoEspacial;
import juego.Gaia;
import juego.Juego;
import juego.Jugador;
import tablero.Coordenada;
import tablero.ITablero;
import tablero.Tablero;

public class EscenarioSimple {

    private static final int W = 29;
    private static final int H = 11;
    private static final Coordenada PUNTO_MEDIO = new Coordenada((W + 1) / 2, (H + 1) / 2);

    private Juego.Builder builder;
    private ITablero mapa;
    private Gaia gaia;
    private Jugador j1;
    private Jugador j2;

    public EscenarioSimple(Juego.Builder builder) {
        this.builder = builder;

        builder.tablero(new Tablero(W, H, builder.gaia()));
    }

    private static Coordenada c(int x, int y) {
        return new Coordenada(x, y);
    }

    private static int simetrica(int x, int medio) {
        return 2 * medio - x;
    }

    private static Coordenada simetrica(Coordenada coord) {
        int x = simetrica(coord.x, PUNTO_MEDIO.x);
        int y = simetrica(coord.y, PUNTO_MEDIO.y);

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

    private void nuevoTerrenoEspacial(Coordenada coord) {
        Ficha terreno = new TerrenoEspacial();
        terreno.setBasico(gaia, mapa, coord);
        terreno.ponerEnJuego();
    }

    private void nuevaUnidadBasica(Jugador jugador, Coordenada coord) {
        Ficha unidad = jugador.raza().nuevaUnidadBasica();
        unidad.setBasico(jugador, mapa, coord);
        unidad.ponerEnJuego();
    }

    private void nuevoTransporte(Jugador jugador, Coordenada coord) {
        Ficha transporte = jugador.raza().nuevoTransporte();
        transporte.setBasico(jugador, mapa, coord);
        transporte.ponerEnJuego();
    }

    public Juego construir() {
        Juego juego = builder.build();

        mapa = juego.tablero();
        gaia = juego.gaia();
        j1 = juego.jugadores().get(0);
        j2 = juego.jugadores().get(1);

        nuevoVolcan(c(1, PUNTO_MEDIO.y));
        nuevoVolcan(simetrica(c(1, PUNTO_MEDIO.y)));

        nuevoMineral(c(1, PUNTO_MEDIO.y - 1));
        nuevoMineral(simetrica(c(1, PUNTO_MEDIO.y - 1)));

        nuevoMineral(c(1, PUNTO_MEDIO.y + 1));
        nuevoMineral(simetrica(c(1, PUNTO_MEDIO.y + 1)));

        nuevoVolcan(PUNTO_MEDIO);
        nuevoMineral(c(PUNTO_MEDIO.x, PUNTO_MEDIO.y - 1));
        nuevoMineral(c(PUNTO_MEDIO.x, PUNTO_MEDIO.y + 1));

        nuevoTerrenoEspacial(c(PUNTO_MEDIO.x, 1));
        nuevoTerrenoEspacial(simetrica(c(PUNTO_MEDIO.x, 1)));

        nuevaUnidadBasica(j1, c(2, PUNTO_MEDIO.y));
        nuevaUnidadBasica(j2, simetrica(c(2, PUNTO_MEDIO.y)));

        nuevoTransporte(j1, c(3, PUNTO_MEDIO.y));
        nuevoTransporte(j2, simetrica(c(3, PUNTO_MEDIO.y)));

        Ficha tormenta = new TormentaPsionicaFicha();
        tormenta.setBasico(j1, mapa, c(5, 4));
        tormenta.ponerEnJuego();

        return juego;
    }

}

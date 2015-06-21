package gui.modelo;

import ficha.Ficha;
import ficha.Fichas;
import tablero.Coordenada;
import tablero.ITablero;

public class FichaParaConstruir {

    private Ficha ficha;
    private final FichaObjetivo fichaObjetivo;
    private final JugadorDeTurno jugadorDeTurno;

    public FichaParaConstruir(FichaObjetivo fichaObjetivo, JugadorDeTurno jugadorDeTurno) {
        this.ficha = null;
        this.fichaObjetivo = fichaObjetivo;
        this.jugadorDeTurno = jugadorDeTurno;
    }

    public void seleccionar(Ficha ficha) {
        this.ficha = ficha;
        this.fichaObjetivo.cambiarAccion(AccionEnGrilla.CONSTRUCCION);
    }

    public void ubicarEn(Coordenada coordenada) {
        ITablero mapa = jugadorDeTurno.juego().tablero();

        Ficha ficha = Fichas.newInstance(this.ficha).enConstruccion();
        ficha.setBasico(jugadorDeTurno.jugador(), mapa, coordenada);
        ficha.ponerEnJuego();

        fichaObjetivo.retornarAccionPorDefecto();
    }
}

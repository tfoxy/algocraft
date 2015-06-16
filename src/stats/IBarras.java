package stats;

import ficha.Ficha;

public interface IBarras extends Cloneable {
    void sufrirDanio(int danio);

    void pasarTurno();

    boolean estaMuerto();

    int vidaActual();

    int escudoActual();

    int energiaActual();

    IBarras espectro();

    IBarras clone();
}

package stats;

public interface IBarras extends Cloneable {
    void sufrirDanio(int danio);

    void pasarTurno();

    boolean estaMuerto();

    int vidaActual();

    int escudoActual();

    int energiaActual();

    int vidaMaxima();

    int escudoMaximo();

    int energiaMaxima();

    int vidaPorTurno();

    int escudoPorTurno();

    int energiaPorTurno();

    IBarras espectro();

    IBarras clone();

    void quitarEnergia(int cantidad);

    String toShortString();
}

package stats;

import ficha.Ficha;

public class BarrasEscudoVidaEnergia {
    private int vidaMaxima;
    private int escudoMaximo;
    private int energiaMaxima;

    private int vidaActual;
    private int escudoActual;
    private int energuiaActual;

    private int vidaPorTurno = 1;
    private int escudoPorTurno = 10;
    private int energiaPorTurno = 10;

    public BarrasEscudoVidaEnergia(int vidaMaxima,
                                   int escudoMaximo,
                                   int energiaMaxima) {
        this.vidaMaxima = vidaMaxima;
        this.escudoMaximo = escudoMaximo;
        this.energiaMaxima = energiaMaxima;
        vidaActual = vidaMaxima;
        escudoActual = escudoMaximo;
        energuiaActual = energiaMaxima;
    }

    public void sufrirDanio(int danio, Ficha ficha) {
        if (0 > escudoActual - danio) {
            vidaActual = vidaActual + (escudoActual - danio);
            escudoActual = 0;
            if (0 >= vidaActual) {
                ficha.muerete();
            }
        }
        escudoActual = (escudoActual - danio);
    }

    public void pasarTurno() {
        vidaActual = vidaActual + vidaPorTurno;
        escudoActual = escudoActual + escudoPorTurno;

        energuiaActual = energuiaActual + energiaPorTurno;
        if (vidaActual > vidaMaxima)
            vidaActual = vidaMaxima;

        if (escudoActual > escudoMaximo)
            escudoActual = escudoMaximo;

        if (energuiaActual > energiaMaxima)
            energuiaActual = energiaMaxima;
    }

}

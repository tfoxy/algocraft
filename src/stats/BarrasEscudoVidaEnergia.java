package stats;

import ficha.Ficha;

//new 6
public class BarrasEscudoVidaEnergia {
    private int vidaMaxima;
    private int escudoMaximo;
    private int energiaMaxima;

    private int vidaActual;
    private int escudoActual;
    private int energiaActual;

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
        energiaActual = energiaMaxima;

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
        vidaActual = Math.min(vidaActual + vidaPorTurno, vidaMaxima);
        escudoActual = Math.min(escudoActual + escudoPorTurno, escudoMaximo);
        energiaActual = Math.min(energiaActual + energiaPorTurno, energiaMaxima);
    }

    public int vidaActual() {
        return vidaActual;
    }

    public int escudoActual() {
        return escudoActual;
    }

}

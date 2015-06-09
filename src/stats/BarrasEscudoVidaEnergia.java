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

    private int vidaPorTurno;
    private int escudoPorTurno;
    private int energiaPorTurno;

    private BarrasEscudoVidaEnergia(Builder builder) {
        vidaMaxima = builder.vidaMaxima;
        escudoMaximo = builder.escudoMaximo;
        energiaMaxima = builder.energiaMaxima;

        vidaActual = builder.vidaActual;
        escudoActual = builder.escudoActual;
        energiaActual = builder.energiaActual;

        vidaPorTurno = builder.vidaPorTurno;
        escudoPorTurno = builder.escudoPorTurno;
        energiaPorTurno = builder.energiaPorTurno;
    }

    public void sufrirDanio(int danio) {
        if (0 > escudoActual - danio) {
            vidaActual = vidaActual + (escudoActual - danio);
            escudoActual = 0;
        } else {
            escudoActual = (escudoActual - danio);
        }
    }

    public void sufrirDanio(int danio, Ficha ficha) {
        sufrirDanio(danio);
        if (this.estaMuerto()) {
            ficha.muerete();
        }
    }

    public void pasarTurno() {
        vidaActual = Math.min(vidaActual + vidaPorTurno, vidaMaxima);
        escudoActual = Math.min(escudoActual + escudoPorTurno, escudoMaximo);
        energiaActual = Math.min(energiaActual + energiaPorTurno, energiaMaxima);
    }

    public boolean estaMuerto() {
        return vidaActual <= 0 && escudoActual <= 0;
    }

    public int vidaActual() {
        return vidaActual;
    }

    public int escudoActual() {
        return escudoActual;
    }

    
    public static class Builder {
        private int vidaMaxima = 0;
        private int escudoMaximo = 0;
        private int energiaMaxima = 0;

        private int vidaActual = 0;
        private int escudoActual = 0;
        private int energiaActual = 0;

        private int vidaPorTurno = 1;
        private int escudoPorTurno = 10;
        private int energiaPorTurno = 10;
        
        public Builder vida(int vida) {
            this.vidaMaxima(vida);
            this.vidaActual(vida);
            return this;
        }
        
        public Builder vidaMaxima(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public Builder vidaActual(int vidaActual) {
            this.vidaActual = vidaActual;
            return this;
        }

        public Builder vidaPorTurno(int vidaPorTurno) {
            this.vidaPorTurno = vidaPorTurno;
            return this;
        }

        public Builder escudo(int escudo) {
            this.escudoMaximo(escudo);
            this.escudoActual(escudo);
            return this;
        }

        public Builder escudoMaximo(int escudoMaximo) {
            this.escudoMaximo = escudoMaximo;
            return this;
        }

        public Builder escudoActual(int escudoActual) {
            this.escudoActual = escudoActual;
            return this;
        }

        public Builder escudoPorTurno(int escudoPorTurno) {
            this.escudoPorTurno = escudoPorTurno;
            return this;
        }

        public Builder energia(int energia) {
            this.energiaMaxima(energia);
            this.energiaActual(energia);
            return this;
        }

        public Builder energiaMaxima(int energiaMaxima) {
            this.energiaMaxima = energiaMaxima;
            return this;
        }

        public Builder energiaActual(int energiaActual) {
            this.energiaActual = energiaActual;
            return this;
        }

        public Builder energiaPorTurno(int energiaPorTurno) {
            this.energiaPorTurno = energiaPorTurno;
            return this;
        }

        public BarrasEscudoVidaEnergia build() {
            return new BarrasEscudoVidaEnergia(this);
        }
    }

}

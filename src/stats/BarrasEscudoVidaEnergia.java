package stats;

import error.EnergiaInsuficienteException;

import java.util.Collections;

public class BarrasEscudoVidaEnergia implements IBarras, Cloneable {
    private static final Builder EMPTY_BUILDER = new Builder();

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

    private BarrasEscudoVidaEnergia() {
        this(EMPTY_BUILDER);
    }

    public BarrasEscudoVidaEnergia(int vida) {
        this();
        vidaMaxima = vidaActual = vida;
    }

    public BarrasEscudoVidaEnergia(int vida, int escudo) {
        this(vida);
        escudoMaximo = escudoActual = escudo;
    }

    public BarrasEscudoVidaEnergia(int vida,
                                   int escudo,
                                   int energiaMaxima,
                                   int energiaActual,
                                   int energiaPorTurno) {
        this(vida, escudo);

        this.energiaMaxima = energiaMaxima;
        this.energiaActual = energiaActual;
        this.energiaPorTurno = energiaPorTurno;
    }

    @Override
    public void sufrirDanio(int danio) {
        if (0 > escudoActual - danio) {
            vidaActual = vidaActual + (escudoActual - danio);
            escudoActual = 0;
        } else {
            escudoActual = (escudoActual - danio);
        }
    }

    @Override
    public void pasarTurno() {
        vidaActual = Math.min(vidaActual + vidaPorTurno, vidaMaxima);
        escudoActual = Math.min(escudoActual + escudoPorTurno, escudoMaximo);
        energiaActual = Math.min(energiaActual + energiaPorTurno, energiaMaxima);
    }

    @Override
    public boolean estaMuerto() {
        return vidaActual <= 0 && escudoActual <= 0;
    }

    @Override
    public int vidaActual() {
        return vidaActual;
    }

    @Override
    public int escudoActual() {
        return escudoActual;
    }

    @Override
    public int energiaActual() {
        return energiaActual;
    }

    @Override
    public int vidaMaxima() {
        return vidaMaxima;
    }

    @Override
    public int escudoMaximo() {
        return escudoMaximo;
    }

    @Override
    public int energiaMaxima() {
        return energiaMaxima;
    }

    @Override
    public int vidaPorTurno() {
        return vidaPorTurno;
    }

    @Override
    public int escudoPorTurno() {
        return escudoPorTurno;
    }

    @Override
    public int energiaPorTurno() {
        return energiaPorTurno;
    }


    public static class Builder {
        private int vidaMaxima = 0;
        private int escudoMaximo = 0;
        private int energiaMaxima = 0;

        private int vidaActual = 0;
        private int escudoActual = 0;
        private int energiaActual = 0;

        private int vidaPorTurno = 1;
        private int escudoPorTurno = 4;
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

    @Override
    public IBarras espectro() {
        BarrasEscudoVidaEnergia clone = this.clone();

        clone.vidaActual = 0; //el morir dice que tiene que tener 0 vida y 0 escudo.
        clone.vidaMaxima = 0;
        clone.vidaPorTurno = 0;

        return clone;
    }

    @Override
    public BarrasEscudoVidaEnergia clone() {

        BarrasEscudoVidaEnergia clone = null;
        try {
            clone = (BarrasEscudoVidaEnergia) super.clone();
        } catch (CloneNotSupportedException e) {
            // No debería ocurrir
        } //cuando esten echos los Texy intentar quitar el (casteo)
        return clone;
    }

    @Override
    public void quitarEnergia(int cantidad) {
        energiaActual = Math.max(0, energiaActual - cantidad);
    }

    @Override
    public String toShortString() {
        StringBuilder builder = new StringBuilder();
        concatBarras(builder, "V", vidaActual, vidaMaxima, vidaPorTurno);
        concatBarras(builder, "E", escudoActual, escudoMaximo, escudoPorTurno);
        concatBarras(builder, "M", energiaActual, energiaMaxima, energiaPorTurno);
        return builder.toString();
    }

    @Override
    public void aplicarEmp() {
        escudoActual = 0;
        energiaActual = 0;
    }

    private void concatBarras(StringBuilder stringBuilder,
                                     String prefix,
                                     int actual,
                                     int maxima,
                                     int porTurno) {
        if (maxima > 0) {
            if (stringBuilder.length() > 0)
                stringBuilder.append(' ');

            String str = String.format("%s:%d/%d", prefix, actual, maxima);
            stringBuilder.append(str);

            if (porTurno > 0)
                stringBuilder.append("(+" + porTurno + ')');
        }
    }

    @Override
    public String toString() {
        return String.format("V:%d/%d(+%d) E:%d/%d(+%d) M:%d/%d(+%d)",
                vidaActual,
                vidaMaxima,
                vidaPorTurno,
                escudoActual,
                escudoMaximo,
                escudoPorTurno,
                energiaActual,
                energiaMaxima,
                energiaPorTurno);
    }
}

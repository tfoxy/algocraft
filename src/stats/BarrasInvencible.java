package stats;

public final class BarrasInvencible implements IBarras {

    public static final BarrasInvencible INSTANCE = new BarrasInvencible();

    private BarrasInvencible() {
        // noop
    }

    @Override
    public void sufrirDanio(int danio) {
        // noop
    }

    @Override
    public void pasarTurno() {
        // noop
    }

    @Override
    public boolean estaMuerto() {
        return false;
    }

    @Override
    public int vidaActual() {
        return 0;
    }

    @Override
    public int escudoActual() {
        return 0;
    }

    @Override
    public int energiaActual() {
        return 0;
    }

    @Override
    public int vidaMaxima() {
        return 0;
    }

    @Override
    public int escudoMaximo() {
        return 0;
    }

    @Override
    public int energiaMaxima() {
        return 0;
    }

    @Override
    public int vidaPorTurno() {
        return 0;
    }

    @Override
    public int escudoPorTurno() {
        return 0;
    }

    @Override
    public int energiaPorTurno() {
        return 0;
    }

    @Override
    public BarrasInvencible espectro() {
        return this;
    }

    @Override
    public BarrasInvencible clone() {
        return this;
    }
}

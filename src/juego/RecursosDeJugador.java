package juego;

import error.MineralInsuficienteException;
import error.GasInsuficienteException;
import error.PoblacionInsuficienteException;

public class RecursosDeJugador {

    private static final int POBLACION_MAXIMA = 200;

    public static class Poblacion {
        private int actual = 0;
        private int posible = 0;
        private int contador = 0;
        private final int maxima;

        public Poblacion() {
            this(POBLACION_MAXIMA);
        }

        public Poblacion(int maxima) {
            this.maxima = maxima;
        }

        public int actual() {
            return actual;
        }

        public int posible() {
            return posible;
        }

        public int maxima() {
            return maxima;
        }

        public void aumentarActualForzadamente(int aumento) {
            this.actual += aumento;
        }

        public void cambiarActual(int cambio) {
            validarSiHaySuficiente(cambio);

            actual += cambio;
        }

        public void cambiarPosible(int cambio) {
            contador += cambio;
            posible = Math.min(maxima, contador);
        }

        public void validarSiHaySuficiente(int aumento) {
            if (!haySuficiente(aumento)) {
                throw new PoblacionInsuficienteException();
            }
        }

        public boolean haySuficiente(int aumento) {
            return actual + aumento <= posible || aumento <= 0;
        }

        @Override
        public String toString() {
            return String.format("%d/%d (%d|%d)", actual, posible, contador, maxima);
        }
    }

    private int mineral;
    private int gas;
    private final Poblacion poblacion;

    public RecursosDeJugador(int mineral, int gas) {
        this(mineral, gas, POBLACION_MAXIMA);
    }

    RecursosDeJugador(int mineral, int gas, int poblacionMaxima) {
        this.mineral = mineral;
        this.gas = gas;
        poblacion = new Poblacion(poblacionMaxima);
    }

    public int mineral() {
        return mineral;
    }

    public int gas() {
        return gas;
    }

    public Poblacion poblacion() {
        return poblacion;
    }

    public void validarSuficientesRecursos(Recursos coste) {
        if (coste.mineral() > mineral) {
            throw new MineralInsuficienteException();
        }
        if (coste.gas() > gas) {
            throw new GasInsuficienteException();
        }
        poblacion.validarSiHaySuficiente(coste.poblacion());
    }

    public boolean haySuficienteRecursos(Recursos coste) {
        return coste.mineral() <= mineral
                && coste.gas() < gas
                && poblacion.haySuficiente(coste.poblacion());
    }

    public void gastar(Recursos coste) {
        validarSuficientesRecursos(coste);

        poblacion.cambiarActual(coste.poblacion());

        mineral -= coste.mineral();
        gas -= coste.gas();
    }

    public Recursos dameRecursosLineales() {
        return new Recursos(mineral, gas);
    }

    public void agregarMineral(int mineral) {
        this.mineral += mineral;
    }

    public void agregarGas(int gas) {
        this.gas += gas;
    }

    @Override
    public String toString() {
        return mineral + "M " + gas + "G & Pob " + poblacion.toString();
    }
}

package juego;

import error.RecursosInsuficientesException;

public class RecursosDeJugador {

    private static final int POBLACION_MAXIMA = 200;

    public static class Poblacion {
        private int actual = 0;
        private int posible = 0;
        private int contador = 0;
        private int maxima = POBLACION_MAXIMA;

        public Poblacion() {

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
            if (cambio <= 0)
                return;

            if (actual + cambio > posible) {
                throw new RecursosInsuficientesException("No hay población disponible");
            }

            actual += cambio;
        }

        public void cambiarPosible(int cambio) {
            contador += cambio;
            posible = Math.min(maxima, contador);
        }

        public boolean haySuficiente(int aumento) {
            return actual + aumento < posible;
        }
    }

    private int mineral;
    private int gas;
    private final Poblacion poblacion;

    RecursosDeJugador(int mineral, int gas) {
        this.mineral = mineral;
        this.gas = gas;
        poblacion = new Poblacion();
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

    public boolean haySuficienteRecursos(Recursos coste) {
        return coste.mineral() <= mineral
                && coste.gas() < gas
                && poblacion.haySuficiente(coste.poblacion());
    }

    public void gastar(Recursos coste) {
        if (coste.mineral() > mineral) {
            throw new RecursosInsuficientesException("Cristal insuficiente");
        }
        if (coste.gas() > gas) {
            throw new RecursosInsuficientesException("Gas insuficiente");
        }

        poblacion.cambiarActual(coste.poblacion());

        mineral = mineral - coste.mineral();
        gas = gas - coste.gas();
    }

    public void agregarMineral(int mineral) {
        mineral += mineral;
    }

    public void agregarGas(int gas) {
        gas += gas;
    }



}

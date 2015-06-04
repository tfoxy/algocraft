package jugador;

public class Recursos {

    private static final int POBLACION_MAXIMA = 200;


    private int cristal = 0;
    private int gas = 0;
    private int poblacionActual = 0;
    private int poblacionPosibleUsable = 0;
    private int poblacionPosibleTotal = 0;


    //constructores//
    public Recursos(int cristal, int gas) {
        this.cristal = cristal;
        this.gas = gas;
    }

    public Recursos(int cristal, int gas, int poblacion) {
        this.cristal = cristal;
        this.gas = gas;
        poblacionActual = poblacion;
    }

    //Gets Usados En Text//

    public int cantidadCristal() {
        return cristal;
    }

    public int cantidadGas() {
        return gas;
    }

    public int poblacionPosible() {
        return poblacionPosibleUsable;
    }

    //este se usa de verdad
    public int poblcacionActual() {
        return poblacionActual;
    }


    //AlteraraRecrusos//
    public void agregarRecursosLineales(int cristal, int gas) {
        this.cristal = this.cristal + cristal;
        this.gas = this.gas + gas;

    }

    public void agregarPoblacionTotal(int aumentoDePoblacion) {
        poblacionPosibleTotal = poblacionPosibleTotal + aumentoDePoblacion;
        if (poblacionPosibleTotal > POBLACION_MAXIMA) {
            poblacionPosibleUsable = POBLACION_MAXIMA;
        } else {
            poblacionPosibleUsable = poblacionPosibleTotal;
        }
    }

    public void perderPoblacionActual(int desensoDePoblacion) {
        poblacionActual = poblacionActual - desensoDePoblacion;
    }

    public void perderPoblacionTotal(int decensoDePoblacion) {
        poblacionPosibleTotal = poblacionPosibleTotal - decensoDePoblacion;
        if (poblacionPosibleTotal > POBLACION_MAXIMA) {
            poblacionPosibleUsable = POBLACION_MAXIMA;
        } else {
            poblacionPosibleUsable = poblacionPosibleTotal;
        }

    }

    public void gastaRecursos(Recursos coste) {
        gas = gas - coste.gas;
        cristal = cristal - coste.cristal;
        poblacionActual = poblacionActual + coste.poblacionActual;
    }


    //ComparaRecursos//
    public boolean tengoSuficientesRecursos(Recursos coste) {
        int poblacionDisponible = poblacionPosibleUsable - poblacionActual;

        return gas >= coste.gas
                && cristal >= coste.cristal
                && poblacionDisponible >= coste.poblacionActual;
    }


}

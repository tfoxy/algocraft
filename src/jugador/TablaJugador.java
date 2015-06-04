package jugador;

import java.util.ArrayList;

import ficha.Ficha;
import tecnologia.ListaDeTecnologias;
import tecnologia.Tecnologia;


public class TablaJugador {

    private ArrayList<Ficha> misFichas = new ArrayList<>();
    private String nombre;
    private Tecnologia raza;
    private Recursos recursos;
    private ListaDeTecnologias tecnologias;


    public TablaJugador(String nombre, Tecnologia raza) {
        this.nombre = nombre;
        this.raza = raza;
        recursos = new Recursos(0, 0, 0);
    }


    //constuctorCompleto
    public TablaJugador(String nombre,
                        Tecnologia raza,
                        int cristalInicial,
                        int gasInicial) {
        this.nombre = nombre;
        this.raza = raza;
        recursos = new Recursos(cristalInicial, gasInicial, 0);
        tecnologias = new ListaDeTecnologias();
        tecnologias.agregar(raza);
    }

    //get para Text//
    public int cantidadCristal() {
        return recursos.cantidadCristal();
    }

    public int cantidadGas() {
        return recursos.cantidadGas();
    }

    public int poblcacionPosible() {
        return recursos.poblacionPosible();
    }

    public int poblcacionActual() {
        return recursos.poblcacionActual();
    }


    //recursos
    public void agregarRecursosLineales(int cristal, int gas) {
        recursos.agregarRecursosLineales(cristal, gas);
    }

    public void gastaRecursos(Recursos coste) {
        recursos.gastaRecursos(coste);
    }

    public void agregarPoblacionTotal(int aumentoDePoblacion) {
        recursos.agregarPoblacionTotal(aumentoDePoblacion);
    }

    public void perderPoblacionTotal(int desensoDePoblacion) {
        recursos.perderPoblacionTotal(desensoDePoblacion);

    }

    public void perderPoblacionActual(int desensoDePoblacion) {
        recursos.perderPoblacionActual(desensoDePoblacion);
    }

    //Ficha
    public void newFicha(Ficha nuevaFicha) {
        misFichas.add(nuevaFicha);

    }

    public void perderFicha(Ficha fichaPerdida) {
        misFichas.remove(fichaPerdida);

    }


    //otros


    public void pasarTurno() {
        for (Ficha object: misFichas) {
            object.pasarTurno();
        }
    }


    public boolean tengoSuficientesRecursos(Recursos coste) {
        return recursos.tengoSuficientesRecursos(coste);
    }

    //tecnologia
    public void agregarTecnologia(Tecnologia tecnologia) {
        tecnologias.agregar(tecnologia);
    }

    public boolean tienesLasTecnologias(ListaDeTecnologias tecnologiasNecesarias) {
        return tecnologias.contengo(tecnologiasNecesarias);
    }


}

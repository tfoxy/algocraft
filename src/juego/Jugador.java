package juego;

import java.util.*;

import error.TecnologiasInsuficientesException;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import tablero.Coordenada;


public class Jugador {

    private final Set<Ficha> fichas;
    private final String nombre;
    private final Raza raza;
    private final RecursosDeJugador recursos;
    private final List<Tecnologia> tecnologias;


    public Jugador(String nombre, Raza raza) {
        this(nombre, raza, 0, 0);
    }


    public Jugador(String nombre,
                   Raza raza,
                   int cristalInicial,
                   int gasInicial) {
        this.nombre = nombre;
        this.raza = raza;

        fichas = Collections.newSetFromMap(new IdentityHashMap<Ficha, Boolean>());

        tecnologias = new ArrayList<>();
        tecnologias.addAll(raza.tecnologiasIniciales());

        recursos = new RecursosDeJugador(cristalInicial, gasInicial);
    }


    public String nombre() {
        return nombre;
    }


    public RecursosDeJugador recursos() {
        return recursos;
    }


    public List<Tecnologia> tecnologias() {
        return tecnologias;
    }


    public void pasarTurno() {
        for (Ficha ficha: fichas) {
            ficha.pasarTurno();
        }
    }


    public void asignar(Ficha ficha) {
        if (!tecnologias.containsAll(ficha.tecnologiasNecesarias())) {
            throw new TecnologiasInsuficientesException();
        }

        int aumentoDePoblacion = ficha.coste().poblacion();

        recursos.poblacion().aumentarActualForzadamente(aumentoDePoblacion);

        this.agregarFicha(ficha);
    }


    public void construir(EdificioTerrestre edificio) {
        recursos.gastar(edificio.coste());

        edificio.enConstruccion();

        this.agregarFicha(edificio);
    }


    public void perder(Ficha ficha) {
        fichas.remove(ficha);
    }


    private void agregarFicha(Ficha ficha) {
        this.fichas.add(ficha);
        ficha.propietario(this);
    }


    public void gastaRecursos(Recursos coste) {
        recursos.gastar(coste);
    }

    public void newFicha(Ficha ficha) {
        asignar(ficha);
    }


    public boolean tengoSuficientesRecursos(Recursos coste) {
        return recursos.haySuficienteRecursos(coste);
    }

    public boolean tienesLasTecnologias(List<Tecnologia> tecnologias) {
        return this.tecnologias.containsAll(tecnologias);
    }

    public void agregarPoblacionTotal(int aumento) {
        recursos.poblacion().cambiarPosible(aumento);
    }

    public void agregarRecursos(Recursos recursos) {
        this.recursos.agregarMineral(recursos.mineral());
        this.recursos.agregarGas(recursos.gas());
    }


    public void perderPoblacionTotal(int cantidad) {
        recursos.poblacion().cambiarPosible(-cantidad);
    }

    public void perderPoblacionActual(int cantidad) {
        recursos.poblacion().cambiarActual(-cantidad);
    }

    public void perderFicha(Ficha ficha) {
        this.perder(ficha);
    }

    public int cantidadGas() {
        return recursos.gas();
    }

    public int cantidadMineral() {
        return recursos.mineral();
    }

    public int poblcacionActual() {
        return recursos.poblacion().actual();
    }

    public int poblacionPosible() {
        return recursos.poblacion().posible();
    }

    public void agregarTecnologia(Tecnologia tecnologia) {
        tecnologias.add(tecnologia);
    }
}

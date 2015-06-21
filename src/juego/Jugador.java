package juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import error.TecnologiasInsuficientesException;
import ficha.Ficha;


public class Jugador {

    private final Set<Ficha> fichas;
    private final String nombre;
    private final Raza raza;
    private final RecursosDeJugador recursos;
    private final List<Tecnologia> tecnologias;
    private boolean pasandoTurno;
    private List<Ficha> fichasAEliminar;
    public Ficha[]  fichasDeMiraza;

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

        pasandoTurno = false;
        fichasAEliminar = new ArrayList<>();
        fichasDeMiraza = new FichasDeRaza().DarLista(raza);
    }


    public String nombre() {
        return nombre;
    }


    public RecursosDeJugador recursos() {
        return recursos;
    }


    public List<Tecnologia> tecnologias() {
        return Collections.unmodifiableList(tecnologias);
    }


    public Raza raza() {
        return raza;
    }


    public void pasarTurno() {
        pasandoTurno = true;

        for (Ficha ficha: fichas) {
            ficha.pasarTurno();
        }

        pasandoTurno = false;
        eliminarFichasPendientes();
    }


    private void eliminarFichasPendientes() {
        fichas.removeAll(fichasAEliminar);
        fichasAEliminar = new ArrayList<>();
    }


    public void perder(Ficha ficha) {
        if (pasandoTurno) {
            fichasAEliminar.add(ficha);
        } else {
            fichas.remove(ficha);
        }
    }


    public void gastaRecursos(Recursos coste) {
        recursos.gastar(coste);
    }

    public void newFicha(Ficha ficha) {
        this.fichas.add(ficha);
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
        perderPoblacionActual(ficha.coste().poblacion());
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

    public void agregarTecnologias(List<Tecnologia> tecnologias) {
        for (Tecnologia tecnologia: tecnologias) {
            agregarTecnologia(tecnologia);
        }
    }

    public Set<Ficha> fichas() {
        return Collections.unmodifiableSet(fichas);
    }

    public void validarTecnologias(List<Tecnologia> tecnologias) {
        if (!this.tecnologias.containsAll(tecnologias)) {
            throw new TecnologiasInsuficientesException();
        }
    }
}

package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import error.NombreDeJugadorEsCortoException;
import ficha.Ficha;


public class Jugador {

    public static final int LONGITUD_MINIMA_DE_NOMBRE = 4;

    private final Set<Ficha> fichas;
    private final String nombre;
    private final Raza raza;
    private final Color color;
    private final RecursosDeJugador recursos;
    private final TecnologiasDelJugador tecnologias;
    private boolean pasandoTurno;
    private List<Ficha> fichasAEliminar;

    public Jugador(String nombre, Raza raza) {
        this(nombre, raza, 0, 0);
    }

    public Jugador(String nombre,
                   Raza raza,
                   int cristalInicial,
                   int gasInicial) {
        this(nombre, raza, new Recursos(cristalInicial, gasInicial), colorAlAzar());
    }

    public Jugador(String nombre,
                   Raza raza,
                   Recursos recursosIniciales,
                   Color color) {
        if (nombre.length() < LONGITUD_MINIMA_DE_NOMBRE) {
            throw new NombreDeJugadorEsCortoException();
        }

        this.nombre = nombre;
        this.raza = raza;
        this.color = color;

        fichas = Collections.newSetFromMap(new IdentityHashMap<Ficha, Boolean>());

        tecnologias = new TecnologiasDelJugador();
        tecnologias.agregar(raza.tecnologiasIniciales());

        recursos = new RecursosDeJugador(recursosIniciales);

        pasandoTurno = false;
        fichasAEliminar = new ArrayList<>();
    }


    private static Color colorAlAzar() {
        int index = new Random().nextInt(ColorDeJugador.LISTA.size());
        return ColorDeJugador.LISTA.get(index);
    }


    public String nombre() {
        return nombre;
    }


    public RecursosDeJugador recursos() {
        return recursos;
    }


    public Raza raza() {
        return raza;
    }


    public Color color() {
        return color;
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


    private void perder(Ficha ficha) {
        if (pasandoTurno) {
            fichasAEliminar.add(ficha);
        } else {
            fichas.remove(ficha);
        }
    }

    public void perderFicha(Ficha ficha) {
        perderPoblacionActual(ficha.coste().poblacion());
        this.perder(ficha);
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
        this.tecnologias.agregar(tecnologia);
    }

    public void agregarTecnologias(List<Tecnologia> tecnologias) {
        this.tecnologias.agregar(tecnologias);
    }

    public Set<Ficha> fichas() {
        return Collections.unmodifiableSet(fichas);
    }

    public void validarTecnologias(Collection<Tecnologia> tecnologias) {
        this.tecnologias.validarTenencia(tecnologias);
    }

    public void quitarTecnologias(Collection<Tecnologia> tecnologias) {
        this.tecnologias.quitar(tecnologias);
    }

    public boolean perdi() {
        return fichas.isEmpty();
    }
}

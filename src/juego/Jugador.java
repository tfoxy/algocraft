package juego;

import java.util.*;

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


}

package jugador;

import java.util.ArrayList;
import java.util.List;

import ficha.Ficha;


public class TablaJugador {

    private ArrayList<Ficha> misFichas = new ArrayList<>();
    private String nombre;
    private Raza raza;
    private Recursos recursos;
    private List<Tecnologia> tecnologias;


    public TablaJugador(String nombre, Raza raza) {
        this.nombre = nombre;
        this.raza = raza;

        tecnologias = new ArrayList<>();
        tecnologias.addAll(raza.tecnologiasIniciales());

        recursos = new Recursos(0, 0, 0);
    }


    public TablaJugador(String nombre,
                        Raza raza,
                        int cristalInicial,
                        int gasInicial) {
        this(nombre, raza);
        recursos = new Recursos(cristalInicial, gasInicial, 0);
    }


    public String nombre() {
        return nombre;
    }


    public Recursos recursos() {
        return recursos;
    }

    public List<Tecnologia> tecnologias() {
        return tecnologias;
    }


    public void pasarTurno() {
        for (Ficha ficha: misFichas) {
            ficha.pasarTurno();
        }
    }


}

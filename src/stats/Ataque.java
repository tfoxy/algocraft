package stats;

/**
 * Contiene el daño y el rango del ataque de la ficha.
 * La ficha contiene dos tipos de ataque: terrestre y aereo.
 * Los objetos son inmutables.
 */
public class Ataque {

    public static final Ataque NULO = new Ataque(0, -1);

    private final int danio;
    private final int rango;

    public Ataque(int danio, int rango) {
        this.danio = danio;
        this.rango = rango;
    }

    public int danio() {
        return danio;
    }

    public int rango() {
        return rango;
    }
}

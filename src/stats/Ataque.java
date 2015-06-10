package stats;

public class Ataque {
    final int danio;
    final int rango;

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

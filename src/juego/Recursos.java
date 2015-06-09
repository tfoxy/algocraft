package juego;

public class Recursos {

    private final int mineral;
    private final int gas;
    private final int poblacion;


    public Recursos(int cristal, int gas) {
        this.mineral = cristal;
        this.gas = gas;
        this.poblacion = 0;
    }

    public Recursos(int cristal, int gas, int poblacion) {
        this.mineral = cristal;
        this.gas = gas;
        this.poblacion = poblacion;
    }


    public int mineral() {
        return mineral;
    }

    public int gas() {
        return gas;
    }

    public int poblacion() {
        return poblacion;
    }

}

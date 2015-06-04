package stats;

public class Stats {

    private int transporte;
    private int vision;
    private int danio;
    private int suministros;
    private int rango;
    private BarrasEscudoVidaEnergia barras;

    public Stats(int transporte,
                 int vision,
                 int danio,
                 int suministros,
                 int rango,
                 BarrasEscudoVidaEnergia barras) {
        this.transporte = transporte;
        this.vision = vision;
        this.danio = danio;
        this.suministros = suministros;
        this.rango = rango;
        this.barras = barras;
    }
}

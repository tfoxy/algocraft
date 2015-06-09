package ficha;


import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Zealot extends UnidadTerrestre {

    public Zealot() {
        nombre = "Zealot";
        vision = 7;
        coste = new Recursos(100, 0);
        // TODO tiempo construccion
        barras = new BarrasEscudoVidaEnergia(100, 60);
        ataqueTierra = 8;
        rangoDeAtaqueTierra = 1;
        // TODO ocupa 2 de población
        // TODO transporte
    }
}

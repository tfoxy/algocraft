package ficha.terrans;

import ficha.UnidadAerea;
import juego.Recursos;
import stats.BarrasEscudoVidaEnergia;

public class Espectro extends UnidadAerea {

    public Espectro() {
        nombre = "Espectro";
        barras = new BarrasEscudoVidaEnergia(120);
        coste = new Recursos(150, 100, 2);
        movimiento = movimientoMaximo = 3;
        ataqueTierra = 8;
        ataqueAire = 20;
        rangoDeAtaqueTierra = rangoDeAtaqueAire = 5;
        // TODO transporte = 0;
        vision = 7;
        tiempoDeConstruccion = 8;
    }
}

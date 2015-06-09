package ficha.protoss;


import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Zealot extends UnidadTerrestre {

    public Zealot() {
        nombre = "Zealot";
        vision = 7;
        coste = new Recursos(100, 0, 2);
        // TODO tiempo construccion
        barras = new BarrasEscudoVidaEnergia(100, 60);
        ataqueTierra = 8;
        rangoDeAtaqueTierra = 1;
        estoyVacio = false;
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        // TODO transporte
    }
}

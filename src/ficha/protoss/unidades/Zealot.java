package ficha.protoss.unidades;


import ficha.UnidadTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import tablero.Direccion;

public class Zealot extends UnidadTerrestre {

    public Zealot() {
        nombre = "Zealot";
        vision = 7;
        coste = new Recursos(100, 0, 2);
        turnosParaCrear = 4;
        barras = new BarrasEscudoVidaEnergia(100, 60);
        ataqueTierra = new Ataque(8, 1);
        estoyVacio = false;
        movimiento = movimientoMaximo = 3;
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        tecnologiasNecesarias.add(Tecnologia.ACCESO);
        // TODO transporte
    }



}

package ficha.protoss;


import estrategia.ficha.EstrategiaConsturccion;
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

    @Override
    public void PonerEnJuego() {
        estrategia = new EstrategiaConsturccion(); //para que te gusta que este viva por defecto.
        estrategia.PonerEnJuego(this);
    }

    @Override
    public void muerete() {
        estrategia.matar(this);
    }
    
    public boolean intentarMovimiento(Direccion dirrecion) {
        return estrategia.intentarMovimiento(this, dirrecion);
    }
}

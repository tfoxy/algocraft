package ficha.terran.unidad;

import ficha.TipoDeFicha;
import ficha.UnidadAerea;
import juego.Recursos;
import juego.Tecnologia;
import magia.EmpMagia;
import magia.RadiacionMagia;
import stats.BarrasEscudoVidaEnergia;

public class NaveCiencia extends UnidadAerea {

    private static final BarrasEscudoVidaEnergia.Builder BARRAS_BUILDER =
            new BarrasEscudoVidaEnergia.Builder()
                    .vida(200)
                    .energiaPorTurno(10)
                    .energiaMaxima(200)
                    .energiaActual(50);

    public NaveCiencia() {
        this.nombre = "Nave Ciencia";
        vision = 10;
        coste = new Recursos(200, 0, 2);
        turnosParaCrear = 10;
        barras = BARRAS_BUILDER.build();
        tipoDeFicha.add(TipoDeFicha.TERRAN);
        tipoDeFicha.add(TipoDeFicha.MAGICA);
        tecnologiasNecesarias.add(Tecnologia.TERRAN);
        tecnologiasNecesarias.add(Tecnologia.PUERTO_ESTELAR);
        movimientoMaximo = 6;
        magias.add(new EmpMagia());
        magias.add(new RadiacionMagia());
    }

}

package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;

import ficha.Ficha;

public class EstrategiaConstruccion extends EstrategiaFicha {

    final EstrategiaFicha siguienteEstrategia;
    int turnosFaltantesParaCrear;

    public EstrategiaConstruccion(Ficha ficha, EstrategiaFicha siguienteEstrategia) {
        super(ficha);
        this.siguienteEstrategia = siguienteEstrategia;
        turnosFaltantesParaCrear = ficha.turnosParaCrear();
    }


    @Override
    public void pasarTurno() {
        // TODO llenar vida de a poco
        turnosFaltantesParaCrear -= 1;
        if (turnosFaltantesParaCrear <= 0) {
            ficha.estrategia(siguienteEstrategia);
        }
    }

}

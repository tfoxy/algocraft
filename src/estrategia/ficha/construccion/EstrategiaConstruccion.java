package estrategia.ficha.construccion;

import estrategia.ficha.EstrategiaFicha;

import ficha.Ficha;

public class EstrategiaConstruccion extends EstrategiaFicha {

    public EstrategiaConstruccion(Ficha ficha) {
        super(ficha);
    }


    @Override
    public EstrategiaFicha pasarTurno() {
        return this;
    }

}

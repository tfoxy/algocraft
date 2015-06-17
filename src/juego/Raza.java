package juego;

import ficha.Ficha;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.unidad.Zealot;
import ficha.terran.edificio.DepositoSuministro;
import ficha.terran.unidad.Marine;

import java.util.Collections;
import java.util.List;

public enum Raza {
    TERRAN,
    PROTOSS;

    static {
        TERRAN.tecnologiasIniciales = Collections.singletonList(Tecnologia.TERRAN);
        TERRAN.unidadBasicaClass = Marine.class;
        TERRAN.casaClass = DepositoSuministro.class;

        PROTOSS.tecnologiasIniciales = Collections.singletonList(Tecnologia.PROTOSS);
        PROTOSS.unidadBasicaClass = Zealot.class;
        PROTOSS.casaClass = Pilon.class;
    }


    private Class<? extends Ficha> unidadBasicaClass;
    private Class<? extends Ficha> casaClass;
    private List<Tecnologia> tecnologiasIniciales;

    public List<Tecnologia> tecnologiasIniciales() {
        return tecnologiasIniciales;
    }

    private Ficha newInstance(Class<? extends Ficha> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public Ficha nuevaUnidadBasica() {
        return newInstance(unidadBasicaClass);
    }

    public Ficha nuevaCasa() {
        return newInstance(casaClass);
    }
}

package juego;

import ficha.Ficha;
import ficha.protoss.unidad.Zealot;
import ficha.terran.unidad.Marine;

import java.util.Arrays;
import java.util.List;

public enum Raza {
    TERRAN(Marine.class, Tecnologia.TERRAN),
    PROTOSS(Zealot.class, Tecnologia.PROTOSS);

    private Class<? extends Ficha> unidadBasicaClass;
    private List<Tecnologia> tecnologiasIniciales;

    Raza(Class<? extends Ficha> unidadBasicaClass, Tecnologia... tecnologiasIniciales) {
        this.unidadBasicaClass = unidadBasicaClass;
        this.tecnologiasIniciales = Arrays.asList(tecnologiasIniciales);
    }

    public List<Tecnologia> tecnologiasIniciales() {
        return tecnologiasIniciales;
    }

    public Ficha nuevaUnidadBasica() {
        try {
            return unidadBasicaClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}

package jugador;

import tecnologia.Tecnologia;

import java.util.Arrays;
import java.util.List;

public enum Raza {
    TERRAN(Tecnologia.TERRAN),
    PROTOSS(Tecnologia.PROTOSS);

    private List<Tecnologia> tecnologiasIniciales;

    private Raza(Tecnologia... tecnologiasIniciales) {
        this.tecnologiasIniciales = Arrays.asList(tecnologiasIniciales);
    }

    public List<Tecnologia> tecnologiasIniciales() {
        return tecnologiasIniciales;
    }
}

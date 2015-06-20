package juego;

import ficha.Ficha;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.unidad.Zealot;
import ficha.terran.edificio.DepositoSuministro;
import ficha.terran.unidad.Marine;

import java.util.Collections;
import java.util.List;

public enum Raza {
    TERRAN {
        private final List<Tecnologia> techs =
                Collections.singletonList(Tecnologia.TERRAN);

        @Override
        public List<Tecnologia> tecnologiasIniciales() {
            return techs;
        }

        @Override
        public Ficha nuevaUnidadBasica() {
            return new Marine();
        }

        @Override
        public Ficha nuevaCasa() {
            return new DepositoSuministro();
        }
    },
    PROTOSS {
        private final List<Tecnologia> techs =
                Collections.singletonList(Tecnologia.PROTOSS);

        @Override
        public List<Tecnologia> tecnologiasIniciales() {
            return techs;
        }

        @Override
        public Ficha nuevaUnidadBasica() {
            return new Zealot();
        }

        @Override
        public Ficha nuevaCasa() {
            return new Pilon();
        }
    };

    public abstract List<Tecnologia> tecnologiasIniciales();

    public abstract Ficha nuevaUnidadBasica();

    public abstract Ficha nuevaCasa();
}

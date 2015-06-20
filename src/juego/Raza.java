package juego;

import ficha.Ficha;
import ficha.protoss.edificio.Acceso;
import ficha.protoss.edificio.ArchivosTemplarios;
import ficha.protoss.edificio.Asimilador;
import ficha.protoss.edificio.NexoMineral;
import ficha.protoss.edificio.Pilon;
import ficha.protoss.edificio.PuertoEstelarProtoss;
import ficha.protoss.unidad.AltoTemplario;
import ficha.protoss.unidad.Dragon;
import ficha.protoss.unidad.NaveTransporteProtoss;
import ficha.protoss.unidad.Scout;
import ficha.protoss.unidad.Zealot;
import ficha.terran.edificio.Barraca;
import ficha.terran.edificio.CentroDeMineral;
import ficha.terran.edificio.DepositoSuministro;
import ficha.terran.edificio.Fabrica;
import ficha.terran.edificio.PuertoEstelarTerran;
import ficha.terran.edificio.Refineria;
import ficha.terran.unidad.Espectro;
import ficha.terran.unidad.Golliat;
import ficha.terran.unidad.Marine;
import ficha.terran.unidad.NaveCiencia;
import ficha.terran.unidad.NaveTransporteTerran;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Raza {
    TERRAN {
        private final List<Tecnologia> techs = Collections.unmodifiableList(
                Collections.singletonList(Tecnologia.TERRAN)
        );

        private final List<Ficha> fichas = Collections.unmodifiableList(
                new ArrayList<Ficha>(Arrays.asList(
                        new CentroDeMineral(),
                        new Refineria(),
                        new DepositoSuministro(),
                        new Barraca(),
                        new Fabrica(),
                        new PuertoEstelarTerran(),
                        new Marine(),
                        new Golliat(),
                        new Espectro(),
                        new NaveCiencia(),
                        new NaveTransporteTerran()
                ))
        );

        @Override
        public List<Tecnologia> tecnologiasIniciales() {
            return techs;
        }

        @Override
        public List<Ficha> listaDeFichas() {
            return fichas;
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
        private final List<Tecnologia> techs = Collections.unmodifiableList(
                Collections.singletonList(Tecnologia.PROTOSS)
        );

        private final List<Ficha> fichas = Collections.unmodifiableList(
                new ArrayList<Ficha>(Arrays.asList(
                        new NexoMineral(),
                        new Asimilador(),
                        new Pilon(),
                        new Acceso(),
                        new PuertoEstelarProtoss(),
                        new ArchivosTemplarios(),
                        new Zealot(),
                        new Dragon(),
                        new Scout(),
                        new AltoTemplario(),
                        new NaveTransporteProtoss()
                ))
        );

        @Override
        public List<Tecnologia> tecnologiasIniciales() {
            return techs;
        }

        @Override
        public List<Ficha> listaDeFichas() {
            return fichas;
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

    public abstract List<Ficha> listaDeFichas();
}

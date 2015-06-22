package juego;

import ficha.Ficha;
import ficha.Fichas;
import ficha.TipoDeFicha;
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
import java.util.EnumSet;
import java.util.List;

public enum Raza {
    TERRAN(TipoDeFicha.TERRAN, Tecnologia.TERRAN),
    PROTOSS(TipoDeFicha.PROTOSS, Tecnologia.PROTOSS);


    private final TipoDeFicha tipoDeFicha;
    private final List<Tecnologia> tecnologiasIniciales;
    private final List<Ficha> listaDeFichas;
    private final Ficha unidadBasica;
    private final Ficha casa;
    private final Ficha transporte;
    private final Ficha unidadMagica;


    Raza(TipoDeFicha tipoDeFicha, Tecnologia tecnologia) {
        this.tipoDeFicha = tipoDeFicha;

        this.tecnologiasIniciales = Collections.unmodifiableList(
                Collections.singletonList(tecnologia)
        );

        this.listaDeFichas = Collections.unmodifiableList(
                Fichas.listaDe(tipoDeFicha)
        );

        this.unidadBasica = Fichas.listaDe(listaDeFichas, TipoDeFicha.BASICA).get(0);
        this.casa = Fichas.listaDe(listaDeFichas, TipoDeFicha.CASA).get(0);
        this.transporte = Fichas.listaDe(listaDeFichas, TipoDeFicha.TRANSPORTE).get(0);
        this.unidadMagica = Fichas.listaDe(listaDeFichas, TipoDeFicha.MAGICA).get(0);
    }


    public List<Tecnologia> tecnologiasIniciales() {
        return tecnologiasIniciales;
    }

    public List<Ficha> listaDeFichas() {
        return listaDeFichas;
    }

    public Ficha nuevaUnidadBasica() {
        return Fichas.newInstance(unidadBasica);
    }

    public Ficha nuevaCasa() {
        return Fichas.newInstance(casa);
    }

    public Ficha nuevoTransporte() {
        return Fichas.newInstance(transporte);
    }

    public Ficha nuevaUnidadMagica() {
        return Fichas.newInstance(unidadMagica);
    }
}

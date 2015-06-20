package ficha;

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
import java.util.Set;

public final class Fichas {
    private static final List<Ficha> FICHAS = Collections.unmodifiableList(
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
                    new NaveTransporteTerran(),

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

    private Fichas() {
        // Utility class
    }

    public static List<Ficha> lista() {
        return FICHAS;
    }

    public static List<Ficha> listaDe(TipoDeFicha tipoDeFicha) {
        return listaDe(EnumSet.of(tipoDeFicha));
    }

    public static List<Ficha> listaDe(Set<TipoDeFicha> tipoDeFicha) {
        return listaDe(FICHAS, tipoDeFicha);
    }

    public static List<Ficha> listaDe(List<Ficha> subset, TipoDeFicha tipoDeFicha) {
        return listaDe(subset, EnumSet.of(tipoDeFicha));
    }

    public static List<Ficha> listaDe(List<Ficha> subset, Set<TipoDeFicha> tipoDeFicha) {
        final List<Ficha> lista = new ArrayList<>();

        for (Ficha ficha: subset) {
            if (ficha.es(tipoDeFicha)) {
                lista.add(ficha);
            }
        }

        return lista;
    }


}

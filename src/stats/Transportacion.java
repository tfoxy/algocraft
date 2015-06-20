package stats;

import error.CapacidadInsuficienteException;
import error.NoSePuedeCargarEntidadConOcupacionCeroException;
import error.TransporteNoContieneFichaException;
import ficha.Ficha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transportacion {

    public static final Transportacion VACIA = new Transportacion(0);

    private final int capacidad;
    private final List<Ficha> fichasCargadas;
    private int espacioUtilizado;

    public Transportacion(int capacidad) {
        this.capacidad = capacidad;

        if (capacidad > 0) {
            fichasCargadas = new ArrayList<>(capacidad);
        } else {
            fichasCargadas = Collections.emptyList();
        }

        espacioUtilizado = 0;
    }

    public void cargar(Ficha ficha) throws CapacidadInsuficienteException {
        if (ficha.ocupacionEnTransporte() == 0) {
            throw new NoSePuedeCargarEntidadConOcupacionCeroException();
        }

        int nuevoEspacioUtilizado = espacioUtilizado + ficha.ocupacionEnTransporte();

        if (nuevoEspacioUtilizado > capacidad) {
            throw new CapacidadInsuficienteException();
        }

        ficha.disminuirMovimiento();

        fichasCargadas.add(ficha);
        espacioUtilizado = nuevoEspacioUtilizado;
    }

    public boolean contieneFicha(Ficha ficha) {
        return fichasCargadas.contains(ficha);
    }

    public void descargar(Ficha ficha) throws TransporteNoContieneFichaException {
        if (!fichasCargadas.remove(ficha)) {
            throw new TransporteNoContieneFichaException();
        }
    }

    public List<Ficha> fichasCargadas() {
        return Collections.unmodifiableList(fichasCargadas);
    }

    public int capacidad() {
        return capacidad;
    }
}

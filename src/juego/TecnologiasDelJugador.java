package juego;

import com.google.common.collect.EnumMultiset;
import error.TecnologiaInsuficienteException;

import java.util.Collection;

public class TecnologiasDelJugador {
    private EnumMultiset<Tecnologia> tecnologias;

    public TecnologiasDelJugador() {
        this.tecnologias = EnumMultiset.create(Tecnologia.class);
    }

    public void agregar(Tecnologia tecnologia) {
        this.tecnologias.add(tecnologia);
    }

    public void agregar(Collection<Tecnologia> tecnologias) {
        this.tecnologias.addAll(tecnologias);
    }

    public void quitar(Tecnologia tecnologia) {
        this.tecnologias.remove(tecnologia);
    }

    public void quitar(Collection<Tecnologia> tecnologias) {
        for (Tecnologia tecnologia: tecnologias) {
            quitar(tecnologia);
        }
    }

    public boolean tiene(Tecnologia tecnologia) {
        return this.tecnologias.contains(tecnologia);
    }

    public boolean tiene(Collection<Tecnologia> tecnologias) {
        return this.tecnologias.containsAll(tecnologias);
    }

    public void validarTenencia(Tecnologia tecnologia) {
        if (!this.tecnologias.contains(tecnologia)) {
            throw new TecnologiaInsuficienteException(tecnologia);
        }
    }

    public void validarTenencia(Collection<Tecnologia> tecnologias) {
        for (Tecnologia tecnologia: tecnologias) {
            validarTenencia(tecnologia);
        }
    }
}

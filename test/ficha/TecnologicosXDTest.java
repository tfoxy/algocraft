package ficha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tecnologia.ListaDeTecnologias;
import tecnologia.Tecnologia;


public class TecnologicosXDTest {

    @Test
    public void tieneEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        assertTrue(tecnologiasDelJugador.contengo(tecnologiasNecesarias));

    }

    @Test
    public void noTieneEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasNecesarias.agregar(Tecnologia.GAIA);
        assertFalse(tecnologiasDelJugador.contengo(tecnologiasNecesarias));

    }

    @Test
    public void tieneEsoySoloeso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        assertTrue(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

    @Test
    public void tieneEsoyMasdeEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        assertTrue(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

    @Test
    public void tieneEsoyEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);

        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.ACCESO);
        assertTrue(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

    @Test
    public void tieneEsoPeroNoEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);

        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.ACCESO);
        assertFalse(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

    public void teniaEso() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasDelJugador.quitar(Tecnologia.PROTOSS);

        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.ACCESO);
        assertFalse(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

    public void aunMeQuedanDeEsos() {
        ListaDeTecnologias tecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias tecnologiasNecesarias = new ListaDeTecnologias();
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasDelJugador.agregar(Tecnologia.ACCESO);
        tecnologiasDelJugador.quitar(Tecnologia.ACCESO);

        tecnologiasNecesarias.agregar(Tecnologia.PROTOSS);
        tecnologiasNecesarias.agregar(Tecnologia.ACCESO);
        assertTrue(tecnologiasDelJugador.contengo(tecnologiasNecesarias));
    }

}

package ficha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Tecnologia.ListaDeTecnologias;
import Tecnologia.Tecnologias;



public class TecnologicosXDTest {

    @Test
    public void TieneEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasNecesarias.Agregar("Protos");
        assertTrue(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));

    }

    @Test
    public void NoTieneEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasNecesarias.Agregar("Zergg");
        assertFalse(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));

    }

    @Test
    public void TieneEsoySoloeso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Protos");
        assertTrue(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

    @Test
    public void TieneEsoyMasdeEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Protos");
        assertTrue(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

    @Test
    public void TieneEsoyEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");

        TecnologiasNecesarias.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Acceso");
        assertTrue(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

    @Test
    public void TieneEsoPeroNoEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");

        TecnologiasNecesarias.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Acceso");
        assertFalse(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

    public void TeniaEso() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasDelJugador.Quitar("Protos");

        TecnologiasNecesarias.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Acceso");
        assertFalse(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

    public void AunMeQuedanDeEsos() {
        ListaDeTecnologias TecnologiasDelJugador = new ListaDeTecnologias();
        ListaDeTecnologias TecnologiasNecesarias = new ListaDeTecnologias();
        TecnologiasDelJugador.Agregar("Protos");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasDelJugador.Agregar("Acceso");
        TecnologiasDelJugador.Quitar("Acceso");

        TecnologiasNecesarias.Agregar("Protos");
        TecnologiasNecesarias.Agregar("Acceso");
        assertTrue(TecnologiasDelJugador.ContengoEstasTecnologias(TecnologiasNecesarias));
    }

}

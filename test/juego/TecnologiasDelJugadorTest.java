package juego;


import error.TecnologiaInsuficienteException;
import org.junit.Before;
import org.junit.Test;

public class TecnologiasDelJugadorTest {

    private TecnologiasDelJugador tecnologiasDelJugador;

    @Before
    public void initialize() {
        tecnologiasDelJugador = new TecnologiasDelJugador();
    }

    @Test
    public void tieneTecnologiaAlAgregarse() {
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.validarTenencia(Tecnologia.PROTOSS);
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noTieneTecnologiaAlNoAgregarse() {
        tecnologiasDelJugador.validarTenencia(Tecnologia.PROTOSS);
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noTieneTecnologiaAlSerAgregadaYQuitada() {
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.quitar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.validarTenencia(Tecnologia.PROTOSS);
    }

    @Test
    public void tieneTecnologiaAlSerAgregadaDosVecesYQuitadaUnaVez() {
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.quitar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.validarTenencia(Tecnologia.PROTOSS);
    }

    @Test(expected = TecnologiaInsuficienteException.class)
    public void noTieneTecnologiaAlSerAgregadaDosVecesYQuitadaDosVeces() {
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.agregar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.quitar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.quitar(Tecnologia.PROTOSS);
        tecnologiasDelJugador.validarTenencia(Tecnologia.PROTOSS);
    }

}

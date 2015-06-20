package juego;

import error.MineralInsuficienteException;
import error.GasInsuficienteException;
import org.junit.Before;
import org.junit.Test;

public class RecursosDeJugadorTest {

    private RecursosDeJugador recursosDeJugador;

    @Before
    public void initialize() {
        recursosDeJugador = new RecursosDeJugador(50, 50, 100);
    }

    @Test
    public void tieneSuficientesRecursos() {
        Recursos coste = new Recursos(10, 20);
        recursosDeJugador.validarSuficientesRecursos(coste);
    }

    @Test(expected = GasInsuficienteException.class)
    public void noTieneSuficientesRecursosPorFaltaDeGas() {
        Recursos coste = new Recursos(10, 100);
        recursosDeJugador.validarSuficientesRecursos(coste);
    }

    @Test(expected = MineralInsuficienteException.class)
    public void noTieneSuficientesRecursosPorFaltaDeMineral() {
        Recursos coste = new Recursos(100, 10);
        recursosDeJugador.validarSuficientesRecursos(coste);
    }


}

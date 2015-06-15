package juego;

import org.junit.Assert;
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
        boolean hayRecursos =
                recursosDeJugador.haySuficienteRecursos(coste);

        Assert.assertTrue(hayRecursos);
    }

    @Test
    public void noTieneSuficientesRecursosPorFaltaDeGas() {
        Recursos coste = new Recursos(10, 100);
        boolean hayRecursos =
                recursosDeJugador.haySuficienteRecursos(coste);

        Assert.assertFalse(hayRecursos);
    }

    @Test
    public void noTieneSuficientesRecursosPorFaltaDeMineral() {
        Recursos coste = new Recursos(100, 10);
        boolean hayRecursos =
                recursosDeJugador.haySuficienteRecursos(coste);

        Assert.assertFalse(hayRecursos);
    }


}

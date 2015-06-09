package juego;

import dummy.UnidadTerrestreDummy;
import ficha.Ficha;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class JugadorTest {

    private Jugador jugador;

    @Before
    public void initialize() {
        jugador = new Jugador("poroto", Raza.PROTOSS);
    }

    @Test
    public void noPasaElTurnoDeLaFichaSiLaPerdio() {
        Ficha unidad = Mockito.spy(new UnidadTerrestreDummy());

        jugador.asignar(unidad);

        jugador.perder(unidad);

        jugador.pasarTurno();

        Mockito.verify(unidad, Mockito.times(0)).pasarTurno();
    }

    @Test
    public void noPierdePrimeraFichaAlEliminarMismoTipoDeFicha() {
        Ficha primerUnidad = Mockito.spy(new UnidadTerrestreDummy());
        Ficha segundaUnidad = Mockito.spy(new UnidadTerrestreDummy());

        jugador.asignar(primerUnidad);
        jugador.asignar(segundaUnidad);

        jugador.perder(segundaUnidad);

        jugador.pasarTurno();

        Mockito.verify(primerUnidad, Mockito.times(1)).pasarTurno();
    }

}

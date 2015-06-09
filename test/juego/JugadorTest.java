package juego;

import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.Zealot;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {

    private Jugador jugador;

    @Before
    public void initialize() {
        jugador = new Jugador("poroto", Raza.PROTOSS);
    }

    @Test
    public void noPierdePrimeraFichaAlEliminarMismoTipoDeFicha() {
        Ficha primerUnidad = new FichaTerrestre();

        jugador.asignar(primerUnidad);
        jugador.asignar(new Zealot());

        jugador.perder(primerUnidad);


    }

}

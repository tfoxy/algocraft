package tablero;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

public class CoordenadaUtilTest {

    @Test
    public void areaDeCoordenadasContieneCoordenadaVecinas() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), 1);

        assertThat(set, hasItems(
                new Coordenada(2, 3),
                new Coordenada(4, 3),
                new Coordenada(3, 2),
                new Coordenada(3, 4)
        ));
    }

    @Test
    public void areaDeCoordenadasNoContieneCoordenadaLejana() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), 1);

        assertThat(set, not(hasItem(new Coordenada(1, 3))));
    }

    @Test
    public void areaDeCoordenadasContieneCoordenadaEnBorde() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), 2);

        assertThat(set, hasItem(new Coordenada(1, 3)));
    }

    @Test
    public void areaDeCoordenadasContieneSolamenteSuCoordenadaSiRangoEsCero() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), 0);

        assertThat(set, hasItem(new Coordenada(3, 3)));
        assertThat(set, hasSize(1));
    }

    @Test
    public void areaDeCoordenadasNoContieneCoordenadasSiRangoEsNegativo() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), -1);

        assertThat(set, hasSize(0));
    }

    @Test
    public void areaDeCoordenadasContieneCoordenadaLejanas() {
        final Set<Coordenada> set =
                CoordenadaUtil.coordenadasEnArea(new Coordenada(3, 3), 3);

        assertThat(set, hasItem(new Coordenada(1, 3)));
        assertThat(set, hasItem(new Coordenada(2, 2)));
        assertThat(set, hasItem(new Coordenada(3, 1)));
        assertThat(set, hasItem(new Coordenada(4, 2)));
        assertThat(set, hasItem(new Coordenada(5, 3)));
        assertThat(set, hasItem(new Coordenada(4, 4)));
        assertThat(set, hasItem(new Coordenada(3, 5)));
        assertThat(set, hasItem(new Coordenada(2, 4)));

    }

}

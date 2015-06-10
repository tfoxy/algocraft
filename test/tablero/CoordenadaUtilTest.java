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
        final Set<Coordenada> coordenadaSet =
                CoordenadaUtil.areaDeCoordenadas(new Coordenada(3, 3), 1);

        assertThat(coordenadaSet, hasItems(
                new Coordenada(2, 3),
                new Coordenada(4, 3),
                new Coordenada(3, 2),
                new Coordenada(3, 4)
        ));
    }

    @Test
    public void areaDeCoordenadasNoContieneCoordenadaLejana() {
        final Set<Coordenada> coordenadaSet =
                CoordenadaUtil.areaDeCoordenadas(new Coordenada(3, 3), 1);

        assertThat(coordenadaSet, not(hasItem(new Coordenada(1, 3))));
    }

    @Test
    public void areaDeCoordenadasContieneCoordenadaEnRango() {
        final Set<Coordenada> coordenadaSet =
                CoordenadaUtil.areaDeCoordenadas(new Coordenada(3, 3), 2);

        assertThat(coordenadaSet, hasItem(new Coordenada(1, 3)));
    }

    @Test
    public void areaDeCoordenadasContieneSolamenteSuCoordenadaSiRangoEsCero() {
        final Set<Coordenada> coordenadaSet =
                CoordenadaUtil.areaDeCoordenadas(new Coordenada(3, 3), 0);

        assertThat(coordenadaSet, hasItem(new Coordenada(3, 3)));
        assertThat(coordenadaSet, hasSize(1));
    }

    @Test
    public void areaDeCoordenadasNoContieneCoordenadasSiRangoEsNegativo() {
        final Set<Coordenada> coordenadaSet =
                CoordenadaUtil.areaDeCoordenadas(new Coordenada(3, 3), -1);

        assertThat(coordenadaSet, hasSize(0));
    }

}

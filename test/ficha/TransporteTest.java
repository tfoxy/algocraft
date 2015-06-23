package ficha;

import error.CapacidadInsuficienteException;
import error.DentroDeTransporteException;
import error.FichaSobreOtraFichaException;
import error.UnicamenteObjetivoPropioException;
import ficha.natural.terreno.TerrenoEspacial;
import ficha.terran.unidad.Marine;
import ficha.terran.unidad.NaveTransporteTerran;
import juego.Jugador;
import juego.Raza;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class TransporteTest {

    private Jugador jugador;
    private Tablero mapa;
    private Coordenada coordenada;
    private Ficha transporte;

    @Before
    public void initialize() {
        jugador = new Jugador("MiNombre", Raza.TERRAN);
        mapa = new Tablero(10, 10);
        coordenada = new Coordenada(5, 5);
        transporte = new NaveTransporteTerran();
        transporte.setBasico(jugador, mapa, coordenada);
        transporte.ponerEnJuego();
    }

    @Test(expected = UnicamenteObjetivoPropioException.class)
    public void noPuedeCargarFichaDeOtroJugador() {
        Jugador otroJugador = new Jugador("SoyOtro", Raza.TERRAN);
        Ficha unidad = new Marine();
        unidad.setBasico(otroJugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
    }

    @Test(expected = UnicamenteObjetivoPropioException.class)
    public void noPuedeCargarFichaQueNoEsteDebajoSuyo() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, new Coordenada(4, 5));
        unidad.ponerEnJuego();

        transporte.cargar();
    }

    @Test
    public void unidadesMuerenAlMorirTransporte() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
        transporte.muerete();

        Assert.assertThat(jugador.fichas(), hasSize(0));
    }

    @Test
    public void unidadesTransportadasSeMuevenJuntoAlTransporte() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
        transporte.mover(Direccion.ARRIBA);

        Assert.assertEquals(transporte.coordenada(), unidad.coordenada());
    }

    @Test
    public void puedeDescargarDondeHayEspacioTerrestre() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
        transporte.descargar(unidad);
    }

    @Test
    public void unidadDescargadaSeEncuentraDebajoDeTransporte() {
        Ficha unidad = new Marine();
        Coordenada3d coord3d = new Coordenada3d(coordenada, unidad.altura());
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
        transporte.descargar(unidad);

        Assert.assertEquals(coord3d, unidad.coordenada());
        Assert.assertSame(unidad, mapa.getFicha(coord3d));
    }

    @Test
    public void puedeInsertarseOtraUnidadDondeEstabaLaUnidadACargar() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(otraUnidad);
    }

    @Test
    public void unidadDebajoDeTransporteCargadoNoMuereAlMorirTransporte() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        otraUnidad.ponerEnJuego();

        transporte.muerete();

        Assert.assertThat(jugador.fichas(), hasSize(1));
    }

    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeDescargarDondeHayTerrenoEspacial() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Ficha terrenoEspacial = new TerrenoEspacial();
        terrenoEspacial.setBasico(jugador, mapa, coordenada);
        mapa.insertar(terrenoEspacial);

        transporte.descargar(unidad);
    }

    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeDescargarDondeHayOtraUnidad() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.descargar(unidad);
    }

    @Test
    public void mueveUnidadAlTransporteAlSerCargada() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Assert.assertEquals(transporte.coordenada(), unidad.coordenada());
    }

    @Test
    public void noMueveLaUnidadSiNoPudoSerDescargada() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        otraUnidad.ponerEnJuego();

        try {
            transporte.descargar(unidad);
            Assert.fail();
        } catch (FichaSobreOtraFichaException e) {
            Assert.assertEquals(transporte.coordenada(), unidad.coordenada());
        }
    }

    @Test(expected = CapacidadInsuficienteException.class)
    public void noPuedeCargarSiEstaEnConstruccion() {
        jugador.cheats().verTodoElMapa(mapa);
        transporte.muerete();

        transporte = new NaveTransporteTerran().enConstruccion();
        transporte.setBasico(jugador, mapa, coordenada);
        FichaTestUtil.cumplirCondicionesDelJugadorParaConstruccion(transporte);
        transporte.ponerEnJuego();

        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
    }

    @Test(expected = DentroDeTransporteException.class)
    public void unidadNoPuedeMoverseDentroDeTransporte() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();

        unidad.mover(Direccion.ARRIBA);
    }

    @Test
    public void unidadPuedeMoverseLuegoDeSerDescargada() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        unidad.ponerEnJuego();

        transporte.cargar();
        transporte.descargar(unidad);

        unidad.mover(Direccion.ARRIBA);
    }
}

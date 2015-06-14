package ficha;

import error.FichaACargarDebeEstarDebajoDeTransporte;
import error.FichaSobreOtraFichaException;
import ficha.terrans.Marine;
import ficha.terrans.unidad.NaveTransporteTerran;
import juego.Jugador;
import juego.Raza;
import org.junit.Before;
import org.junit.Test;
import tablero.Coordenada;
import tablero.Tablero;

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
        mapa.insertar(coordenada, transporte);
    }

    @Test // TODO noPuedeCargarFichaDeOtroJugador (tirar Exception)
    public void noPuedeCargarFichaDeOtroJugador() {
        Jugador otroJugador = new Jugador("SoyOtro", Raza.TERRAN);
        Ficha unidad = new Marine();
        unidad.setBasico(otroJugador, mapa, coordenada);
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);
    }

    @Test(expected = FichaACargarDebeEstarDebajoDeTransporte.class)
    public void noPuedeCargarFichaQueNoEsteDebajoSuyo() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, new Coordenada(4, 5));
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);
    }

    @Test
    public void unidadesMuerenAlMorirTransporte() {
        // TODO unidadesMuerenAlMorirTransporte
    }

    @Test
    public void unidadesTransportadasSeMuevenJuntoAlTransporte() {
        // TODO unidadesTransportadasSeMuevenJuntoAlTransporte
    }

    @Test
    public void unidadDebajoDeTransporteCargadoNoMuereAlMorirTransporte() {
        // TODO unidadDebajoDeTransporteCargadoNoMuereAlMorirTransporte
    }

    @Test
    public void puedeDescargarDondeHayEspacioTerrestre() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);
        transporte.descargar(unidad);
    }

    @Test
    public void puedeInsertarseOtraUnidadDondeEstabaLaUnidadACargar() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, otraUnidad);
    }

    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeDescargarDondeHayTerrenoEspacial() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);

        Ficha terrenoEspacial = new FichaEspacial();
        terrenoEspacial.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, terrenoEspacial);

        transporte.descargar(unidad);
    }

    @Test(expected = FichaSobreOtraFichaException.class)
    public void noPuedeDescargarDondeHayOtraUnidad() {
        Ficha unidad = new Marine();
        unidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, unidad);

        transporte.cargar(unidad);

        Ficha otraUnidad = new Marine();
        otraUnidad.setBasico(jugador, mapa, coordenada);
        mapa.insertar(coordenada, otraUnidad);

        transporte.descargar(unidad);
    }
}
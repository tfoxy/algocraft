package ficha;

import estrategia.ficha.EstrategiaFicha;
import estrategia.ficha.construccion.EstrategiaConstruccion;
import juego.Juego;
import juego.Recursos;
import juego.Jugador;
import stats.BarrasEscudoVidaEnergia;
import tablero.Coordenada;
import tablero.Movimiento;
import tablero.Tablero;
import juego.Tecnologia;

import java.util.List;


public abstract class Ficha {

    protected Jugador propietario; //despues cualquier cosa refactorisamos... pero sino es absurdo tener tantos Gets
    protected Tablero tablero;
    protected Coordenada coordenada;

    protected boolean estoyVacio = true;

    protected EstrategiaFicha estrategia = null;
    protected String nombre = null;


    protected Recursos coste = null;
    protected BarrasEscudoVidaEnergia barras = null;
    protected List<Tecnologia> tecnologiasNecesarias = null;
    protected int turnosParaCrear = 0;

    protected FuenteDeRecurso fuenteDeRecursos = null;
    protected Recursos recursosVirgenes = null;
    protected Recursos recursosExtraidosPorTurno = null;
    protected int poblacionQueDa = 0;
    protected String tipoDeFuentaDeRecursos = null;

    protected int movimiento = 0;
    protected int movimientoMaximo = 0;


    /* TODO agregar transporte
    protected List<Ficha> fichasTransportadas = null;
    protected int transporteMaximo = 0;
    protected int ocupacionEnTransporte = 0;
    */

    // TODO agregar magias: List<Magia>

    // TODO stats.Ataque
    protected int ataqueTierra = 0;
    protected int ataqueAire = 0;

    protected int rangoDeAtaqueTierra = 0;
    protected int rangoDeAtaqueAire = 0;

    protected int vision = 0;
    protected int tiempoDeConstruccion = 0;


    //gets
    public Jugador propietario() {
        return propietario;
    }

    public int poblacionQueDa() {
        return poblacionQueDa;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Coordenada coordenada() {
        return coordenada;
    }

    public Recursos coste() {
        return coste;
    }

    public FuenteDeRecurso fuenteDeRecursos() {
        return fuenteDeRecursos;
    }

    public List<Tecnologia> tecnologiasNecesarias() {
        return tecnologiasNecesarias;
    }

    public int turnosParaCrear() {
        return turnosParaCrear;
    }

    public BarrasEscudoVidaEnergia barras() {
        return barras;
    }

    public int rangoDeAtaqueTierra() {
        return rangoDeAtaqueTierra;
    }

    public int ataqueTierra() {
        return ataqueTierra;
    }

    public int rangoDeAtaqueAire() {
        return rangoDeAtaqueAire;
    }

    public int ataqueAire() {
        return ataqueAire;
    }

    public Recursos recursosExtraidosPorTurno() {
        return recursosExtraidosPorTurno;
    }

    public Recursos recursosVirgenes() {
        return recursosVirgenes;
    }

    public String tipoDeFuentaDeRecursos() {
        return tipoDeFuentaDeRecursos;
    }

    public boolean estoyVacio() {
        return estoyVacio;
    }

    public boolean puedoReemplazarFicha(Ficha ficha) {
        // TODO puedoReemplazarFicha
        return false;
    }

    public Jugador propietario(Jugador jugador) {
        return propietario = jugador;
    }

    public void enConstruccion() {
        estrategia = new EstrategiaConstruccion(this, estrategia);
    }

    public void setCoordenada(Coordenada nuevaUbicacion) {
        coordenada = nuevaUbicacion;
    }

    public void fuenteDeRecursos(FuenteDeRecurso recurso) {
        fuenteDeRecursos = recurso;
    }

    public void estrategia(EstrategiaFicha estrategia) {
        this.estrategia = estrategia;
    }


    //Y la magia de la Extrategia
    public void muerete() {
        //y por la magia del polimorfismos el metodo save como matar a todos los tipos de unidades.
        estrategia.matar(this);
    }


    public void pasarTurno() {
        estrategia.pasarTurno(this); //y por la magia del polimorfismos el metodo save como matar a todos los tipos de unidades.
    }

    public void serAtacado(int danio) { //en si esto solo se utalisaria para text. Ataca no lo utiliza.
        estrategia.serAtacado(danio, this);
    }

    public void atacar(Ficha defensor) {
        estrategia.atacar(this, defensor);
    }

    public void PonerEnJuego() {
        estrategia.PonerEnJuego(this);
    }

    public void intentarMovimiento(Movimiento dirrecion) {
        estrategia.intentarMovimiento(this, dirrecion);
    }

}

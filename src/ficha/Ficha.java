package ficha;

import estrategia.ficha.EstrategiaConsturccion;
import estrategia.ficha.EstrategiaFicha;
import juego.Recursos;
import juego.Jugador;
import stats.BarrasEscudoVidaEnergia;
import tablero.Coordenada;
import tablero.Direccion;
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
    protected String tipoDeFuenteDeRecursos = null;

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
        return tipoDeFuenteDeRecursos;
    }

    public boolean estoyVacio() {
        return estoyVacio;
    }

    public boolean estaVacia() {
        return estoyVacio();
    }

    public int movimiento() {
        return movimiento;
    }

    public int movimientoMaximo() {
        return movimientoMaximo;
    }

    public boolean puedoReemplazarFicha(Ficha ficha) {
        // TODO puedoReemplazarFicha
        return false;
    }

    public Jugador propietario(Jugador jugador) {
        return propietario = jugador;
    }

    public void enConstruccion() {
        estrategia = new EstrategiaConsturccion();
    }

    public void setCoordenada(Coordenada nuevaUbicacion) {
        coordenada = nuevaUbicacion;
    }

    public void fuenteDeRecursos(FuenteDeRecurso recurso) {
        fuenteDeRecursos = recurso;
    }

    public void turnosParaCrear(int turnosParaCrear) {
        this.turnosParaCrear = turnosParaCrear;
    }


    //Y la magia de la Extrategia
    public void muerete() {
        //y por la magia del polimorfismos el metodo save como matar a todos los tipos de unidades.
        estrategia.matar(this);
    }


    public void pasarTurno() {
        estrategia = estrategia.pasarTurno(this);
    }

    public void serAtacado(int danio) {
        //en si esto solo se utalisaria para text. Ataca no lo utiliza.
        estrategia.serAtacado(danio, this);
    }

    public void atacar(Ficha defensor) {
        estrategia.atacar(this, defensor);
    }

    public void PonerEnJuego() {
        estrategia.PonerEnJuego(this);
    }

    public void intentarMovimiento(Direccion dirrecion) {
        estrategia.intentarMovimiento(this, dirrecion);
    }

}

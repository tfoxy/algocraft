package ficha;

import error.CapacidadInsuficienteException;
import error.FichaSobreOtraFichaException;
import error.FueraDeRangoException;
import error.JuegoException;
import error.MovimientoInsuficienteException;
import error.NoSePuedeCrearFicha;
import error.RecursosInsuficientesException;
import error.TecnologiasInsuficientesException;
import error.TransporteNoContieneFichaException;
import juego.Recursos;
import juego.Jugador;
import juego.RecursosDeJugador;
import stats.Ataque;
import stats.BarrasEscudoVidaEnergia;
import stats.Transportacion;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;
import juego.Tecnologia;

import java.util.ArrayList;
import java.util.List;


public abstract class Ficha implements Cloneable /*agregar en unidades que quiera clonar, osea solo las Protos.*/{

    protected Jugador propietario; //despues cualquier cosa refactorisamos... pero sino es absurdo tener tantos Gets
    protected Tablero tablero;
    protected Coordenada coordenada;
    protected Coordenada3d coordenada2; //la idea es implemetar las funciones con esta cordenada asta tener todas implementadas y borrar la cordenada anterior

    protected boolean estoyVacio = true;
    protected boolean esNatural = true;
    protected boolean estoyConstruido = false;
    
    protected String nombre = null;

    protected Recursos coste = Recursos.SIN_COSTE;
    protected BarrasEscudoVidaEnergia barras = null;
    protected List<Tecnologia> tecnologiasNecesarias = new ArrayList<>();
    protected List<Tecnologia> tecnologiasQueDa = new ArrayList<>();
    protected int turnosParaCrear = 0;

    protected FuenteDeRecurso fuenteDeRecursos = null;
    protected RecursosDeJugador recursosVirgenes = null;
    protected Recursos recursosExtraidosPorTurno = null;
    protected int poblacionQueDa = 0;
    
    protected String tipoDeFuenteDeRecursos = null;
    protected String tipoDeFuenteDeRecursosQueNecesito = null; //sino puede construir sobre otro edificio XD.
    
    protected int movimiento = 0;
    protected int movimientoMaximo = 0;


    protected Transportacion transportacion = Transportacion.VACIA;
    protected int ocupacionEnTransporte = 0;

    // TODO agregar magias: List<Magia>

    protected Ataque ataqueTierra = Ataque.NULO;
    protected Ataque ataqueAire = Ataque.NULO;

    protected int vision = 0;


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

    public Coordenada3d coordenada() {
        return new Coordenada3d(coordenada, altura());
    }

    public Recursos coste() {
        return coste;
    }//estas pueden ser inutiles si se les agrega construccion op a las fichas.
/*
    public FuenteDeRecurso fuenteDeRecursos() {
        return fuenteDeRecursos;
    }
*/
    public List<Tecnologia> tecnologiasNecesarias() {
        return tecnologiasNecesarias;
    }

    public int turnosParaCrear() {
        return turnosParaCrear;
    }

    public BarrasEscudoVidaEnergia barras() {
        return barras;
    }
/*
    public int rangoDeAtaqueTierra() {
        return ataqueTierra.rango();
    }

    public int ataqueTierra() {
        return ataqueTierra.danio();
    }

    public int rangoDeAtaqueAire() {
        return ataqueTierra.rango();
    }

    public int ataqueAire() {
        return ataqueTierra.danio();
    }

    public Recursos recursosExtraidosPorTurno() {
        return recursosExtraidosPorTurno;
    }
*/
    public RecursosDeJugador recursosVirgenes() {
        return recursosVirgenes;
    }

    public String tipoDeFuentaDeRecursos() {
        return tipoDeFuenteDeRecursos;
    }

    public boolean estoyVacio() {
        return estoyVacio;
    }

    public int movimiento() {
        return movimiento;
    }

    public int movimientoMaximo() {
        return movimientoMaximo;
    }
/*
    public boolean puedoReemplazarFicha(Ficha ficha) {
        // TODO puedoReemplazarFicha
        return false;
    }
*/
    public Jugador propietario(Jugador jugador) {
        return propietario = jugador;
    }
    
    //si me olvido de comenetar. Se agrega esto. Puede que despues se cambie por un build.
    public void setBasico (Jugador jugador, Tablero mapa, Coordenada lugar) {
        propietario = jugador;
        tablero = mapa;
        coordenada = lugar; //puede que en un rebuild esta linea se balla. o toda la funcion.
        coordenada2 = new Coordenada3d(coordenada, 1);
    }

    public void coordenada(Coordenada nuevaUbicacion) {
        coordenada = nuevaUbicacion;
    }

    public void fuenteDeRecursos(FuenteDeRecurso recurso) {
        fuenteDeRecursos = recurso;
    }

    public void turnosParaCrear(int turnosParaCrear) {
        this.turnosParaCrear = turnosParaCrear;
    }


    //muerete
    public void muerete() {
        propietario.perderPoblacionActual(coste.poblacion());
        propietario.perderFicha(this);
        tablero.eliminarFicha(coordenada2);
    }



    public void tablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void disminuirMovimiento() {
        if (movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }

        movimiento -= 1;
    }

    public void recuperarPuntosDeMovimiento() {
        movimiento = movimientoMaximo;
    }
    
 // atacar y defender
    
    public void serAtacado(int danio) {
        barras.sufrirDanio(danio, this);
    }


    public void realizarAtaque( Ficha defensor) {
        final Ataque ataque = defensor.tipoDeAtaqueRecibido(this);

        if (!this.puedoAtacar(defensor, ataque.rango())) {
            throw new FueraDeRangoException();
        }

        defensor.serAtacado(ataque.danio());
    }


    public boolean atacar(Ficha defensor) {
        try {
            this.realizarAtaque(defensor);
            return true;
        } catch (FueraDeRangoException e) {
            return false;
        }
    }


    private boolean puedoAtacar( Ficha defensor, int rango) {
        Coordenada posicionAgresor = coordenada;
        Coordenada posicionDefensor = defensor.coordenada();

        return rango >= posicionAgresor.distanciaAObjetivo(posicionDefensor);
    }
    
    public abstract Ataque tipoDeAtaqueRecibido(Ficha atacante);
// atacar y defender
    
    public boolean esNatural() {
        return esNatural;
    }

    public abstract int altura();
    
    public Ficha clone(){
    	
    	Ficha clone = null;
    	try {
			clone = (Ficha)super.clone();
		} catch (CloneNotSupportedException e) {
			// No debería ocurrir
		} //cuando esten echos los Texy intentar quitar el (casteo)
    	clone.barras = this.barras.clone();
    	
        return this;
    }
    
    public Ficha expectro(){
    	
    	Ficha clone = this.clone();

    	clone.barras = this.barras.expectro();
    	clone.ataqueAire = new Ataque(0,ataqueAire.rango());
    	clone.ataqueTierra = new Ataque(0,ataqueTierra.rango());
    	
        return this;
    }
    
    //poner En juego
    
    public void setBasico2 (Jugador jugador, Tablero mapa, Coordenada3d lugar) {
        propietario = jugador;
        tablero = mapa;
        coordenada2 = lugar;
    }// una idea hacer que el SetBasico use el setBasico2 pero que medienta plimorfismo se fije si es una ficha terrestre o area.
    
    //el nuevo
    public void ponerEnJuego() {
    	/*this.sePuedeCrear(); 
    	propietario.gastaRecursos(coste);
        propietario.newFicha2(this);
        tablero.insertar(coordenada2, this);
    	*/
    	// tener en cuenta la opcion de arriba.
    	
    	
        if (this.sePuedeCrear()) {
        	propietario.gastaRecursos(coste);
            propietario.newFicha2(this);
            tablero.insertar(coordenada2, this);
        }
    }

    public boolean sePuedeCrear() throws NoSePuedeCrearFicha {
        if (!(propietario.tengoSuficientesRecursos(coste))) {
            throw new RecursosInsuficientesException();
        }
        if (!(tablero.hayEspacio(coordenada2))) {
            throw new FichaSobreOtraFichaException();
        }
        if (!(propietario.tienesLasTecnologias(tecnologiasNecesarias()))) {
            throw new TecnologiasInsuficientesException();
        }
        return true;
    }

    public int ocupacionEnTransporte() {
        return ocupacionEnTransporte;
    }


    public void descargar(Ficha ficha) {
        throw new TransporteNoContieneFichaException();
    }

    public void cargar(Ficha ficha) throws CapacidadInsuficienteException {
        throw new CapacidadInsuficienteException();
    }

    //poner En juego
   
    //mover
    public boolean intentarMovimiento(Direccion direccion) {
        try {
            this.mover(direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover( Direccion direccion) {
    	/*if (!estoyConstruido){
    		throw new FichaNoLista();
    	}*/
        Tablero mapa = tablero;
        Coordenada3d ubicacion = coordenada2;
        Coordenada3d nuevaUbicacion = ubicacion.dameCordenadaHacia(direccion);

        if (movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }

        mapa.insertar(nuevaUbicacion, this);
        mapa.eliminarFicha(ubicacion);
        this.disminuirMovimiento();
    }
    //mover
    
    
    //pasarTurnos
    
    public void pasarTurno() {
        turnosParaCrear = turnosParaCrear - 1; //cualqueier cosa que se pase de largo
        barras.pasarTurno();
        this.recuperarPuntosDeMovimiento();
        this.revisarEventos();
    }

    public void revisarEventos() {
    	if(turnosParaCrear == 0)	{
    		this.construir();
    	}
    }
    
    public void construir(){
     	   estoyConstruido = true; // esta funcion crece en otras claces.
         }
    //pasarTurnos
    
    //temporal
	public Coordenada coordenada2() {
		return this.coordenada2;
	}
    

   
    
}

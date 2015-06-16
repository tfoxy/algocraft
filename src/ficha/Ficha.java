package ficha;

import error.CapacidadInsuficienteException;
import error.FichaSobreOtraFichaException;
import error.FueraDeRangoException;
import error.JuegoException;
import error.MovimientoInsuficienteException;
import error.RecursosInsuficientesException;
import error.TecnologiasInsuficientesException;
import error.TransporteNoContieneFichaException;
import juego.Recursos;
import juego.Jugador;
import juego.RecursosDeJugador;
import stats.Ataque;
import stats.BarrasInvencible;
import stats.IBarras;
import stats.Transportacion;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.Tablero;
import juego.Tecnologia;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


public abstract class Ficha implements Cloneable {
    /* agregar Cloneable en unidades que quiera clonar, o sea solo las Protoss.*/

    protected Jugador propietario;
    protected Tablero tablero;
    protected Coordenada3d coordenada;

    protected Set<TipoDeFicha> tipoDeFicha = EnumSet.noneOf(TipoDeFicha.class);
    protected TipoDeFicha tipoDeFichaNecesaria = TipoDeFicha.VACIA;

    protected String nombre = null;

    protected Recursos coste = Recursos.SIN_COSTE;
    protected IBarras barras = BarrasInvencible.INSTANCE;
    protected List<Tecnologia> tecnologiasNecesarias = new ArrayList<>();
    protected List<Tecnologia> tecnologiasQueDa = new ArrayList<>();
    protected int turnosParaCrear = 0;

    protected FuenteDeRecurso fuenteDeRecursos = null;
    protected RecursosDeJugador recursosVirgenes = null;
    protected Recursos recursosExtraidosPorTurno = null;
    protected int poblacionQueDa = 0;

    protected int movimiento = 0;
    protected int movimientoMaximo = 0;


    protected Transportacion transportacion = Transportacion.VACIA;
    protected int ocupacionEnTransporte = 0;

    protected Ataque ataqueTierra = Ataque.NULO;
    protected Ataque ataqueAire = Ataque.NULO;

    protected int vision = 0;

    protected FichaStrategy estrategia = new FichaStrategy();


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
        return coordenada;
    }

    public Recursos coste() {
        return coste;
    }

    public List<Tecnologia> tecnologiasNecesarias() {
        return tecnologiasNecesarias;
    }

    public int turnosParaCrear() {
        return turnosParaCrear;
    }

    public IBarras barras() {
        return barras;
    }

    public RecursosDeJugador recursosVirgenes() {
        return recursosVirgenes;
    }

    public boolean estoyVacio() {
        return tipoDeFicha.contains(TipoDeFicha.VACIA);
    }

    public int movimiento() {
        return movimiento;
    }

    public int movimientoMaximo() {
        return movimientoMaximo;
    }

    protected void coordenada(Coordenada lugar) {
        coordenada = new Coordenada3d(lugar, altura());
    }

    public void setBasico(Jugador jugador, Tablero mapa, Coordenada lugar) {
        propietario = jugador;
        tablero = mapa;
        coordenada(lugar);
    }


    public void muerete() {
        estrategia.muerete();
        propietario.perderFicha(this);
        tablero.eliminarFichaEn(coordenada);
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

    public void sufrirDanio(int danio) {
        barras.sufrirDanio(danio);

        if (barras.estaMuerto()) {
            this.muerete();
        }
    }

    public void atacar(Ficha defensor) {
        final Ataque ataque = defensor.tipoDeAtaqueRecibido(this);

        if (!this.puedoAtacar(defensor, ataque.rango())) {
            throw new FueraDeRangoException();
        }

        defensor.sufrirDanio(ataque.danio());
    }

    public boolean intentarAtaque(Ficha defensor) {
        try {
            this.atacar(defensor);
            return true;
        } catch (FueraDeRangoException e) {
            return false;
        }
    }

    private boolean puedoAtacar(Ficha defensor, int rango) {
        Coordenada posicionAgresor = coordenada;
        Coordenada posicionDefensor = defensor.coordenada();

        return rango >= posicionAgresor.distanciaAObjetivo(posicionDefensor);
    }

    public abstract Ataque tipoDeAtaqueRecibido(Ficha atacante);

    public abstract int altura();

    @Override
    public Ficha clone() {
        Ficha clone = null;
        try {
            clone = (Ficha) super.clone();
        } catch (CloneNotSupportedException e) {
            // No debería ocurrir
            throw new RuntimeException();
        } //cuando esten echos los Texy intentar quitar el (casteo)

        clone.barras = this.barras.clone();
        clone.transportacion = new Transportacion(transportacion.capacidad());

        return this;
    }

    public Ficha espectro() {
        Ficha clone = this.clone();

        clone.barras = this.barras.espectro();
        clone.ataqueAire = new Ataque(0, ataqueAire.rango());
        clone.ataqueTierra = new Ataque(0, ataqueTierra.rango());
        clone.transportacion = Transportacion.VACIA;

        return this;
    }


    public void ponerEnJuego() {
        estrategia.validarCreacion();
        estrategia.gastarRecursos();
        estrategia.crear();
        propietario.newFicha(this);
        tablero.insertar(this);
    }

    public Ficha enConstruccion() {
        estrategia = new ConstruccionStrategy();
        return this;
    }


    public int ocupacionEnTransporte() {
        return ocupacionEnTransporte;
    }

    public void descargar(Ficha ficha) throws TransporteNoContieneFichaException {
        throw new TransporteNoContieneFichaException();
    }

    public void cargar(Ficha ficha) throws CapacidadInsuficienteException {
        throw new CapacidadInsuficienteException();
    }

    //mover
    public boolean intentarMovimiento(Direccion direccion) {
        try {
            this.mover(direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover(Direccion direccion) {
        Coordenada3d ubicacion = coordenada;
        Coordenada3d nuevaUbicacion = ubicacion.dameCordenadaHacia(direccion);

        if (movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }

        verificarCoordenada(nuevaUbicacion);

        coordenada = nuevaUbicacion;

        tablero.insertar(this);
        tablero.eliminarFichaEn(ubicacion);
        this.disminuirMovimiento();
    }

    public void pasarTurno() {
        estrategia.pasarTurno();
    }

    public boolean es(TipoDeFicha tipoDeFicha) {
        return this.tipoDeFicha.contains(tipoDeFicha);
    }

    public void propietario(Jugador propietario) {
        this.propietario = propietario;
    }

    protected boolean puedoReemplazarFichaEnTablero() {
        return puedoReemplazarFichaEnTablero(coordenada);
    }

    protected boolean puedoReemplazarFichaEnTablero(Coordenada3d nuevaCoordenada) {
        return tablero.getFicha(nuevaCoordenada).tipoDeFicha.contains(tipoDeFichaNecesaria);
    }

    protected void verificarCoordenada(Coordenada3d nuevaCoordenada) {
        if (!puedoReemplazarFichaEnTablero(nuevaCoordenada)) {
            throw new FichaSobreOtraFichaException();
        }
    }


    protected class FichaStrategy {

        public void validarCreacion() {
            if (!puedoReemplazarFichaEnTablero()) {
                throw new FichaSobreOtraFichaException();
            }
        }

        public void gastarRecursos() {
            propietario.recursos().poblacion().aumentarActualForzadamente(coste.poblacion());
        }

        public void crear() {
            propietario.agregarPoblacionTotal(poblacionQueDa);
        }

        public void pasarTurno() {
            barras.pasarTurno();
            recuperarPuntosDeMovimiento();
        }

        public void muerete() {
            propietario.perderPoblacionTotal(poblacionQueDa);
        }
    }


    private class ConstruccionStrategy extends FichaStrategy {
        private int turnosFaltantes = turnosParaCrear;
        private FichaStrategy estrategiaAnterior = estrategia;

        @Override
        public void validarCreacion() {
            if (!(propietario.tengoSuficientesRecursos(coste))) {
                throw new RecursosInsuficientesException();
            }
            if (!(propietario.tienesLasTecnologias(tecnologiasNecesarias()))) {
                throw new TecnologiasInsuficientesException();
            }
            super.validarCreacion();
        }

        @Override
        public void gastarRecursos() {
            propietario.gastaRecursos(coste);
        }

        @Override
        public void crear() {
            // noop
        }

        @Override
        public void pasarTurno() {
            turnosFaltantes -= 1;

            // TODO a medida que se vaya construyendo, ir aumentando la vida

            if (turnosFaltantes <= 0) {
                estrategia = estrategiaAnterior;
                estrategia.crear();
            }
        }

        @Override
        public void muerete() {
            // noop
        }
    }


}

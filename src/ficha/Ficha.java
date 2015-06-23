package ficha;

import error.CapacidadInsuficienteException;
import error.DentroDeTransporteException;
import error.EstadoYaExisteEnFichaException;
import error.FichaSobreOtraFichaException;
import error.FueraDeRangoException;
import error.JuegoException;
import error.MovimientoInsuficienteException;
import error.UnicamenteObjetivoNoEnConstruccionException;
import error.TransporteNoContieneFichaException;
import error.UnicamenteObjetivoPropioException;
import ficha.estado.EstadoDeFicha;
import juego.Recursos;
import juego.Jugador;
import juego.RecursosDeJugador;
import magia.Magia;
import stats.Ataque;
import stats.BarrasInvencible;
import stats.IBarras;
import stats.Transportacion;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.ITablero;
import juego.Tecnologia;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class Ficha implements Cloneable {
    /* agregar Cloneable en unidades que quiera clonar, o sea solo las Protoss.*/

    protected Jugador propietario;
    protected ITablero tablero;
    protected Coordenada3d coordenada;

    protected Set<TipoDeFicha> tipoDeFicha = EnumSet.noneOf(TipoDeFicha.class);
    protected TipoDeFicha tipoDeFichaNecesaria = TipoDeFicha.VACIA;

    protected String nombre = "";

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
    protected boolean dentroDeTransporte = false;
    protected boolean estaEnConstruccion = false;
    protected Set<EstadoDeFicha> estados = new HashSet<>();

    protected List<Magia> magias = new ArrayList<>();

    protected FichaStrategy estrategia = new DefaultFichaStrategy();


    //gets
    public Jugador propietario() {
        return propietario;
    }

    public ITablero tablero() {
        return tablero;
    }

    public Coordenada3d coordenada() {
        return coordenada;
    }

    public Recursos coste() {
        return coste;
    }

    public List<Tecnologia> tecnologiasNecesarias() {
        return Collections.unmodifiableList(tecnologiasNecesarias);
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

    public void setBasico(Jugador jugador, ITablero mapa, Coordenada lugar) {
        propietario = jugador;
        tablero = mapa;
        coordenada(lugar);
    }


    public void muerete() {
        estrategia.muerete();
        propietario.perderFicha(this);

        tablero.eliminarFichaEn(coordenada);

        for (Ficha ficha: fichasCargadas()) {
            ficha.muerete();
        }
    }

    public void disminuirMovimiento() {
        validarMovimientoSuficiente();

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

        validarAtaque(defensor, ataque);

        disminuirMovimiento();

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

    private boolean puedoAtacar(Ficha defensor, Ataque ataque) {
        Coordenada posicionAgresor = coordenada;
        Coordenada posicionDefensor = defensor.coordenada();
        int distancia = posicionAgresor.distanciaAObjetivo(posicionDefensor);

        return ataque.rango() >= distancia;
    }

    private void validarAtaque(Ficha defensor, Ataque ataque) {
        if (!this.puedoAtacar(defensor, ataque)) {
            throw new FueraDeRangoException();
        }
    }

    public abstract Ataque tipoDeAtaqueRecibido(Ficha atacante);

    public abstract int altura();

    @Override
    public Ficha clone() {
        Ficha clone;
        try {
            clone = (Ficha) super.clone();
        } catch (CloneNotSupportedException e) {
            // No debería ocurrir
            throw new RuntimeException(e.getMessage(), e.getCause());
        } //cuando esten echos los Texy intentar quitar el (casteo)

        clone.barras = this.barras.clone();
        clone.transportacion = new Transportacion(this.transportacion.capacidad());
        clone.tipoDeFicha = EnumSet.copyOf(this.tipoDeFicha);
        clone.estados = new HashSet<>(this.estados);

        return clone;
    }

    public Ficha espectro() {
        Ficha clone = this.clone();

        estrategia.clonarEn(clone);

        clone.nombre = "Alucinación de " + this.nombre;
        clone.barras = this.barras.espectro();
        clone.ataqueAire = new Ataque(0, ataqueAire.rango());
        clone.ataqueTierra = new Ataque(0, ataqueTierra.rango());
        clone.transportacion = Transportacion.VACIA;
        clone.magias = Collections.emptyList();
        clone.tipoDeFicha.add(TipoDeFicha.ALUCINACION);
        clone.estados = new HashSet<>();
        clone.poblacionQueDa = 0;

        return clone;
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

    public int capacidadEnTransporte() {
        return transportacion.capacidad();
    }

    public List<Ficha> fichasCargadas() {
        return transportacion.fichasCargadas();
    }

    public void validarMovimientoSuficiente() {
        if (movimiento <= 0) {
            throw new MovimientoInsuficienteException();
        }
    }

    private void validarDescarga(Ficha ficha) {
        if (!transportacion.contieneFicha(ficha)) {
            throw new TransporteNoContieneFichaException();
        }

        ficha.validarMovimientoSuficiente();

        Coordenada3d nuevaCoordenada = new Coordenada3d(coordenada, ficha.altura());

        this.verificarCoordenada(nuevaCoordenada);
    }

    private void desembarcar() {
        tablero.insertar(this);
        this.disminuirMovimiento();
        dentroDeTransporte = false;
    }

    public void descargar(Ficha ficha) {
        this.validarDescarga(ficha);

        ficha.coordenada(coordenada.proyeccion());

        ficha.desembarcar();
        transportacion.descargar(ficha);
    }

    public void cargar() throws CapacidadInsuficienteException {
        Ficha fichaACargar = tablero.getFichaTerrestre(coordenada.proyeccion());

        if (!fichaACargar.propietario.equals(propietario)) {
            throw new UnicamenteObjetivoPropioException();
        }

        transportacion.cargar(fichaACargar);

        tablero.eliminarFichaEn(fichaACargar.coordenada);
        fichaACargar.coordenada = coordenada;
        fichaACargar.dentroDeTransporte = true;
    }

    public boolean estaDentroDeTransporte() {
        return dentroDeTransporte;
    }

    public boolean intentarMovimiento(Direccion direccion) {
        try {
            this.mover(direccion);
            return true;
        } catch (JuegoException e) {
            return false;
        }
    }

    public void mover(Direccion direccion) {
        final Coordenada3d ubicacion = coordenada;
        final Coordenada3d nuevaUbicacion = ubicacion.dameCoordenadaHacia(direccion);

        verificarMovimientoHacia(nuevaUbicacion);

        coordenada = nuevaUbicacion;

        tablero.insertar(this);
        tablero.eliminarFichaEn(ubicacion);
        this.disminuirMovimiento();

        for (Ficha ficha: fichasCargadas()) {
            ficha.coordenada = coordenada;
        }
    }

    private void verificarMovimientoHacia(Coordenada3d nuevaUbicacion) {
        if (dentroDeTransporte) {
            throw new DentroDeTransporteException();
        }

        validarMovimientoSuficiente();

        verificarCoordenada(nuevaUbicacion);
    }

    public void pasarTurno() {
        aplicarEstados();
        estrategia.pasarTurno();
    }

    private void aplicarEstados() {
        for (EstadoDeFicha estado: estados) {
            estado.aplicarEn(this);
        }
    }

    public void agregarEstado(EstadoDeFicha estado) {
        if (!this.estados.add(estado)) {
            throw new EstadoYaExisteEnFichaException();
        }
    }

    public boolean es(TipoDeFicha tipoDeFicha) {
        return this.tipoDeFicha.contains(tipoDeFicha);
    }

    public boolean es(Set<TipoDeFicha> tipoDeFicha) {
        return this.tipoDeFicha.containsAll(tipoDeFicha);
    }

    public void propietario(Jugador propietario) {
        this.propietario = propietario;
    }

    protected boolean puedoReemplazarFichaEnTablero() {
        return puedoReemplazarFichaEnTablero(coordenada);
    }

    public boolean puedoReemplazarFichaEnTablero(Coordenada3d nuevaCoordenada) {
        return tablero.getFicha(nuevaCoordenada).tipoDeFicha.contains(tipoDeFichaNecesaria);
    }

    protected void verificarCoordenada(Coordenada3d nuevaCoordenada) {
        if (!puedoReemplazarFichaEnTablero(nuevaCoordenada)) {
            throw new FichaSobreOtraFichaException();
        }
    }

    public String nombre() {
        return nombre;
    }

    public List<Magia> magias() {
        return magias;
    }

    public Collection<Tecnologia> tecnologiasQueDa() {
        return Collections.unmodifiableList(tecnologiasQueDa);
    }


    protected interface FichaStrategy {
        void validarCreacion();
        void gastarRecursos();
        void crear();
        void pasarTurno();
        void muerete();
        void clonarEn(Ficha ficha);
    }

    protected class DefaultFichaStrategy implements FichaStrategy {
        @Override
        public void validarCreacion() {
            if (!puedoReemplazarFichaEnTablero()) {
                throw new FichaSobreOtraFichaException();
            }
        }

        @Override
        public void gastarRecursos() {
            propietario.recursos().poblacion().aumentarActualForzadamente(coste.poblacion());
        }

        @Override
        public void crear() {
            propietario.agregarPoblacionTotal(poblacionQueDa);
            propietario.agregarTecnologias(tecnologiasQueDa);
            recuperarPuntosDeMovimiento();
        }

        @Override
        public void pasarTurno() {
            barras.pasarTurno();
            recuperarPuntosDeMovimiento();
        }

        @Override
        public void muerete() {
            propietario.perderPoblacionTotal(poblacionQueDa);
            propietario.quitarTecnologias(tecnologiasQueDa);
        }

        @Override
        public void clonarEn(Ficha ficha) {
            ficha.estrategia = ficha.new DefaultFichaStrategy();
        }
    }


    private class ConstruccionStrategy extends DefaultFichaStrategy {
        private int turnosFaltantes = turnosParaCrear;
        private FichaStrategy estrategiaAnterior = estrategia;
        private Transportacion transportacionAnterior = transportacion;
        private int visionAnterior = vision;
        private Ataque ataqueTierraAnterior = ataqueTierra;
        private Ataque ataqueAireAnterior = ataqueAire;
        private int movimientoMaximoAnterior = movimientoMaximo;
        private List<Magia> magiasAnteriores = magias;

        private void invalidarPropiedades() {
            transportacion = Transportacion.VACIA;
            vision = 0;
            ataqueTierra = Ataque.NULO;
            ataqueAire = Ataque.NULO;
            movimientoMaximo = 0;
            magias = Collections.emptyList();
        }

        private void revalidarPropiedades() {
            transportacion = transportacionAnterior;
            vision = visionAnterior;
            ataqueTierra = ataqueTierraAnterior;
            ataqueAire = ataqueAireAnterior;
            movimientoMaximo = movimientoMaximoAnterior;
            magias = magiasAnteriores;
        }

        @Override
        public void validarCreacion() {
            propietario.recursos().validarSuficientesRecursos(coste);
            propietario.validarTecnologias(tecnologiasNecesarias);
            estrategiaAnterior.validarCreacion();
        }

        @Override
        public void gastarRecursos() {
            propietario.gastaRecursos(coste);
        }

        @Override
        public void crear() {
            estaEnConstruccion = true;
            invalidarPropiedades();
        }

        @Override
        public void pasarTurno() {
            turnosFaltantes -= 1;

            // TODO a medida que se vaya construyendo, ir aumentando la vida
            barras.pasarTurno();

            if (turnosFaltantes <= 0) {
                estaEnConstruccion = false;
                estrategia = estrategiaAnterior;
                revalidarPropiedades();
                estrategia.crear();
            }
        }

        @Override
        public void muerete() {
            // noop
        }

        @Override
        public void clonarEn(Ficha ficha) {
            throw new UnicamenteObjetivoNoEnConstruccionException();
        }
    }

    public Color miColor() {
        Color color = propietario.color();
        if (estaEnConstruccion)
            color = color.brighter();
        return color;
    }


}

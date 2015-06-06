package ficha;

import jugador.Recursos;
import jugador.TablaJugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FichaDeJugador extends FichaAbstracta {
    private final String nombre;
    private final Recursos coste;

    private int vida;
    private final int vidaMaxima;
    private final int regeneracionDeVida;

    private int escudo;
    private final int escudoMaximo;
    private final int regeneracionDeEscudo;

    private int energia;
    private final int energiaMaxima;
    private final int regeneracionDeEnergia;

    private int movimiento;
    private final int movimientoMaximo;


    private final List<FichaDeJugador> fichasTransportadas;
    private final int transporteMaximo;
    private final int ocupacionEnTransporte;



    // TODO agregar magias: List<Magia>

    private final int ataqueTierra;
    private final int ataqueAire;

    private final int rangoAtaqueTierra;
    private final int rangoAtaqueAire;

    private final int visibilidad;

    // TODO agregar unidades posibles para crear: List<FichaDeJugador>



    private FichaDeJugador(FichaBuilder builder, TablaJugador propietario) {
        super(propietario);

        this.nombre = builder.nombre;
        this.coste = builder.coste;

        this.vida = builder.vida;
        this.vidaMaxima = builder.vidaMaxima;
        this.regeneracionDeVida = builder.regeneracionDeVida;

        this.escudo = builder.escudo;
        this.escudoMaximo = builder.escudoMaximo;
        this.regeneracionDeEscudo = builder.regeneracionDeEscudo;

        this.energia = builder.energia;
        this.energiaMaxima = builder.energiaMaxima;
        this.regeneracionDeEnergia = builder.regeneracionDeEnergia;

        this.movimiento = builder.movimiento;
        this.movimientoMaximo = builder.movimiento;

        this.ocupacionEnTransporte = builder.ocupacionEnTransporte;
        this.transporteMaximo = builder.transporteMaximo;

        this.ataqueTierra = builder.ataqueTierra;
        this.ataqueAire = builder.ataqueAire;

        this.rangoAtaqueTierra = builder.rangoAtaqueTierra;
        this.rangoAtaqueAire = builder.rangoAtaqueAire;

        this.visibilidad = builder.visibilidad;

        fichasTransportadas = builder.transporteMaximo > 0
                ? new ArrayList<>()
                : Collections.EMPTY_LIST;
    }

    @Override
    public String getNombre() {
        return nombre;
    }



    @Override
    public void pasarTurno() {
        // TODO observer
    }



    public static class FichaBuilder {
        private static final String NOMBRE_POR_DEFECTO = "undefined";
        private static final Recursos COSTE_POR_DEFECTO = new Recursos(0, 0);
        private static final int VIDA_POR_DEFECTO = 50;
        private static final int REGENERACION_DE_VIDA_POR_DEFECTO = 0;
        private static final int REGENERACION_DE_ESCUDO_POR_DEFECTO = 10;
        private static final int REGENERACION_ENERGIA_POR_DEFECTO = 10;


        private String nombre = NOMBRE_POR_DEFECTO;
        private Recursos coste = COSTE_POR_DEFECTO;

        private int vida = VIDA_POR_DEFECTO;
        private int vidaMaxima = VIDA_POR_DEFECTO;
        private int regeneracionDeVida = REGENERACION_DE_VIDA_POR_DEFECTO;

        private int escudo = 0;
        private int escudoMaximo = 0;
        private int regeneracionDeEscudo = REGENERACION_DE_ESCUDO_POR_DEFECTO;

        private int energia = 0;
        private int energiaMaxima = 0;
        private int regeneracionDeEnergia = REGENERACION_ENERGIA_POR_DEFECTO;

        private int movimiento = 0;

        private int ocupacionEnTransporte = 0;
        private int transporteMaximo = 0;

        // TODO agregar magias: private List<Magia> magias

        private int ataqueTierra = 0;
        private int ataqueAire = 0;

        private int rangoAtaqueTierra = 0;
        private int rangoAtaqueAire = 0;

        private int visibilidad = 0;

        // TODO agregar unidades posibles para crear: List<FichaDeJugador>


        public FichaBuilder() {
            // noop
        }

        public FichaBuilder nombre(String nombre) {
            if (nombre == null) {
                throw new NullPointerException();
            }

            this.nombre = nombre;

            return this;
        }

        public FichaBuilder coste(Recursos coste) {
            if (nombre == null) {
                throw new NullPointerException();
            }

            this.coste = coste;

            return this;
        }

        public FichaBuilder vida(int vida) {
            this.vidaInicial(vida);
            this.vidaMaxima(vida);
            return this;
        }

        public FichaBuilder vidaInicial(int vidaInicial) {
            this.vida = vidaInicial;
            return this;
        }

        public FichaBuilder vidaMaxima(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public FichaBuilder regeneracionDeVida(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public FichaBuilder escudo(int escudo) {
            this.vidaInicial(escudo);
            this.vidaMaxima(escudo);
            return this;
        }

        public FichaBuilder escudoInicial(int escudoInicial) {
            this.escudo = escudoInicial;
            return this;
        }

        public FichaBuilder escudoMaximo(int escudoMaximo) {
            this.escudoMaximo = escudoMaximo;
            return this;
        }

        public FichaBuilder ocupacionEnTransporte(int ocupacionEnTransporte) {
            this.ocupacionEnTransporte = ocupacionEnTransporte;
            return this;
        }

        public FichaBuilder transporteMaximo(int transporteMaximo) {
            this.transporteMaximo = transporteMaximo;
            return this;
        }

        public FichaBuilder ataque(int ataque) {
            this.ataqueTierra(ataque);
            this.ataqueAire(ataque);
            return this;
        }

        public FichaBuilder ataqueTierra(int ataqueTierra) {
            this.ataqueTierra = ataqueTierra;
            return this;
        }

        public FichaBuilder ataqueAire(int ataqueAire) {
            this.ataqueAire = ataqueAire;
            return this;
        }
        
        public FichaBuilder visibilidad(int visibilidad) {
            this.visibilidad = visibilidad;
            return this;
        }

        public FichaDeJugador crear(TablaJugador propietario) {
            return new FichaDeJugador(this, propietario);
        }

    }
}

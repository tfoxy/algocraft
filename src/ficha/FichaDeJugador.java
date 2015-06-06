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

    private final int rangoDeAtaqueTierra;
    private final int rangoDeAtaqueAire;

    private final int vision;
    private final int tiempoDeConstruccion;

    // TODO agregar unidades posibles para crear: List<FichaDeJugador>



    private FichaDeJugador(Builder builder, TablaJugador propietario) {
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

        this.rangoDeAtaqueTierra = builder.rangoDeAtaqueTierra;
        this.rangoDeAtaqueAire = builder.rangoDeAtaqueAire;

        this.vision = builder.vision;
        this.tiempoDeConstruccion = builder.tiempoDeConstruccion;

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



    public static class Builder {
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

        private int rangoDeAtaqueTierra = 0;
        private int rangoDeAtaqueAire = 0;

        private int vision = 0;
        private int tiempoDeConstruccion = 0;

        // TODO agregar unidades posibles para crear: List<FichaDeJugador>


        public Builder() {
            // noop
        }

        public Builder nombre(String nombre) {
            if (nombre == null) {
                throw new NullPointerException();
            }

            this.nombre = nombre;

            return this;
        }

        public Builder coste(Recursos coste) {
            if (nombre == null) {
                throw new NullPointerException();
            }

            this.coste = coste;

            return this;
        }

        public Builder vida(int vida) {
            this.vidaInicial(vida);
            this.vidaMaxima(vida);
            return this;
        }

        public Builder vidaInicial(int vidaInicial) {
            this.vida = vidaInicial;
            return this;
        }

        public Builder vidaMaxima(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public Builder regeneracionDeVida(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public Builder escudo(int escudo) {
            this.vidaInicial(escudo);
            this.vidaMaxima(escudo);
            return this;
        }

        public Builder escudoInicial(int escudoInicial) {
            this.escudo = escudoInicial;
            return this;
        }

        public Builder escudoMaximo(int escudoMaximo) {
            this.escudoMaximo = escudoMaximo;
            return this;
        }

        public Builder ocupacionEnTransporte(int ocupacionEnTransporte) {
            this.ocupacionEnTransporte = ocupacionEnTransporte;
            return this;
        }

        public Builder transporteMaximo(int transporteMaximo) {
            this.transporteMaximo = transporteMaximo;
            return this;
        }

        public Builder ataque(int ataque) {
            this.ataqueTierra(ataque);
            this.ataqueAire(ataque);
            return this;
        }

        public Builder ataqueTierra(int ataqueTierra) {
            this.ataqueTierra = ataqueTierra;
            return this;
        }

        public Builder ataqueAire(int ataqueAire) {
            this.ataqueAire = ataqueAire;
            return this;
        }

        public Builder rangoDeAtaque(int ataque) {
            this.rangoDeAtaqueTierra(ataque);
            this.rangoDeAtaqueAire(ataque);
            return this;
        }

        public Builder rangoDeAtaqueTierra(int rangoDeAtaqueTierra) {
            this.rangoDeAtaqueTierra = rangoDeAtaqueTierra;
            return this;
        }

        public Builder rangoDeAtaqueAire(int rangoDeAtaqueAire) {
            this.rangoDeAtaqueAire = rangoDeAtaqueAire;
            return this;
        }
        
        public Builder vision(int vision) {
            this.vision = vision;
            return this;
        }

        public Builder movimiento(int movimiento) {
            this.movimiento = movimiento;
            return this;
        }

        public Builder tiempoDeConstruccion(int tiempoDeConstruccion) {
            this.tiempoDeConstruccion = tiempoDeConstruccion;
            return this;
        }

        public FichaDeJugador crear(TablaJugador propietario) {
            return new FichaDeJugador(this, propietario);
        }

    }
}

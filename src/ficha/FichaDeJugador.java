package ficha;

import jugador.Recursos;
import jugador.TablaJugador;

import java.util.List;

import estrategia.ficha.EstrategiaFicha;

import stats.BarrasEscudoVidaEnergia;
import tablero.Coordenada;
import tablero.Tablero;

public class FichaDeJugador extends FichaAbstracta {
   
	public FichaDeJugador(TablaJugador jugador) {
		super(jugador);
	}


	protected EstrategiaFicha estrategia = null;
    protected String nombre = null;
    protected Recursos coste = null;
    protected BarrasEscudoVidaEnergia barras = null;


    protected int movimiento = 0;
    protected int movimientoMaximo = 0;


    protected List<FichaDeJugador> fichasTransportadas = null; //hayAlgoRaroAca
    protected int transporteMaximo = 0;
    protected int ocupacionEnTransporte = 0;
    
    

    // TODO agregar magias: List<Magia>

    protected int ataqueTierra = 0;
    protected int ataqueAire = 0;

    protected int rangoDeAtaqueTierra = 0;
    protected int rangoDeAtaqueAire = 0;

    protected int vision = 0;
    protected int tiempoDeConstruccion = 0;

    // TODO agregar unidades posibles para crear: List<FichaDeJugador>


    @Override
    public String getNombre() {
        return nombre;
    }

    
    public void pasarTurno() {
        // TODO observer para pasarTurno
    }


	public BarrasEscudoVidaEnergia getbarras() {
		return barras;
	}


	public int getAtaqueTierra() {
		return ataqueTierra;
	}


	public int getRangoDeAtaqueTierra() {
		return rangoDeAtaqueTierra;
	}


	public int getRangoDeAtaqueAire() {
		return rangoDeAtaqueAire;
	}

    @Override
    public int getMovimientoMaximo() {
        return movimientoMaximo;
    }

    @Override
    public int getMovimiento() {
        return movimiento;
    }
}

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


	private EstrategiaFicha estrategia = null;
	private String nombre = null;
    private Recursos coste = null;
    private BarrasEscudoVidaEnergia barras = null;


    private int movimiento = 0;
    private int movimientoMaximo = 0;


    private List<FichaDeJugador> fichasTransportadas = null; //hayAlgoRaroAca
    private int transporteMaximo = 0;
    private int ocupacionEnTransporte = 0;
    
    

    // TODO agregar magias: List<Magia>

    private int ataqueTierra = 0;
    private int ataqueAire = 0;

    private int rangoDeAtaqueTierra = 0;
    private int rangoDeAtaqueAire = 0;

    private int vision = 0;
    private int tiempoDeConstruccion = 0;

    // TODO agregar unidades posibles para crear: List<FichaDeJugador>


    @Override
    public String getNombre() {
        return nombre;
    }

    
    public void pasarTurno() {
        // TODO observer para pasarTurno
    }

}

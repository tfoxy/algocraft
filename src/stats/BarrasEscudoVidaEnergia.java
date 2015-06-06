package stats;

import ficha.Ficha;

//new 6
public class BarrasEscudoVidaEnergia {
	private int vidaMaxima;
	private int escudoMaximo;
	private int energuiaMaxima;
	
	private int vidaActual;
	private int escudoActual;
	private int energuiaActual;
	
	private int vidaPorTurno= 1;
	private int escudoPorTurno= 10;
	private int energuiaPorTurno= 10;

	public BarrasEscudoVidaEnergia (int VidaMaxima, int EscudoMaximo, int EnerguiaMaxima)
	{
		vidaMaxima = VidaMaxima;
		escudoMaximo = EscudoMaximo;
		energuiaMaxima = EnerguiaMaxima;
		vidaActual = VidaMaxima;
		escudoActual= EscudoMaximo;
		energuiaActual = EnerguiaMaxima;
		
	}

	public void sufrirDaño (int daño, Ficha ficha)
	{
		if ( 0>escudoActual - daño)
		{
			vidaActual = vidaActual + (escudoActual - daño);
			escudoActual = 0;
			if ( 0>= vidaActual)
			{
				ficha.muerete();
			}
		}
		escudoActual = (escudoActual - daño);
	}
	
	public void pasarTurno ()
	{
		vidaActual = vidaActual + vidaPorTurno;
		escudoActual= escudoActual + escudoPorTurno;
		energuiaActual = energuiaActual + energuiaPorTurno;
		if ( vidaActual > vidaMaxima)
			vidaActual = vidaMaxima;

		if ( escudoActual > escudoMaximo)
			escudoActual = escudoMaximo;
		
		if ( energuiaActual > energuiaMaxima)
			energuiaActual = energuiaMaxima;
	}

	public int vidaActual() {
		return 	vidaActual;
	}

	public int escudoActual() {
		return escudoActual;
	}

}

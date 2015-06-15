package ficha.protoss.edificios;


import ficha.EdifcioDeRecusosTerrestre;
import juego.Recursos;
import juego.Tecnologia;
import stats.BarrasEscudoVidaEnergia;

public class Asimilador extends EdifcioDeRecusosTerrestre {

    public Asimilador() {
        nombre = "Asimilador";
        coste = new Recursos(100, 0);
        turnosParaCrear = 6;
        barras = new BarrasEscudoVidaEnergia(450, 450);
        tecnologiasNecesarias.add(Tecnologia.PROTOSS);
        estoyVacio = false;

        tipoDeFuenteDeRecursos = "Volcan";// esto se puede ir a futuro.
        tipoDeFuenteDeRecursosQueNecesito = "Volcan";
        recursosExtraidosPorTurno = new Recursos (0,10);
    }

	@Override
	public Recursos extraer() {
		// TODO Auto-generated method stub
		return null;
	}

}

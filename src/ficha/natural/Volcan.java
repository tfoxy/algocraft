package ficha.natural;

import error.FichaSobreOtraFichaException;
import error.NoSePuedeCrearFicha;
import error.RecursosInsuficientesException;
import error.TecnologiasInsuficientesException;
import estrategia.ficha.EstrategiaConsturccion;
import ficha.FuenteDeRecurso;
import juego.Recursos;
import tablero.Coordenada;
import tablero.Tablero;

public class Volcan extends FuenteDeRecurso {

    private static final int GAS_POR_DEFECTO = 5000;

    public Volcan() {
        this(GAS_POR_DEFECTO);
        tipoDeFuenteDeRecursos = "Volcan";
        coste = new Recursos(0, 0, 0);
    }

    public Volcan(int gas) {
        super(gas);

        tipoDeFuenteDeRecursos = "Volcan";
        coste = new Recursos(0, 0, 0);
    }

    @Override
    public Recursos recursosVirgenes() {
        return new Recursos(0, cantidadDeRecursos);
    }
    
    public boolean sePuedeCrear() throws NoSePuedeCrearFicha { //es esto o dos cath. Igual hay que revisar mas adelante que pasa si tenes la cantidad exatcta de recurssos.
        if (!(tablero.hayEspacio(coordenada2))) {
            throw new FichaSobreOtraFichaException();
        }
        return true;
    }
}


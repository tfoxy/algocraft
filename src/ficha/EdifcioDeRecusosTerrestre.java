package ficha;

import error.EdificioDeRecursosNecesitaFichaRecursoException;
import error.FichaSobreOtraFichaException;
import error.JuegoException;
import error.NoSePuedeCrearFicha;
import error.RecursosInsuficientesException;
import error.TecnologiasInsuficientesException;
import juego.Recursos;

public abstract class EdifcioDeRecusosTerrestre extends EdificioTerrestre {

    public abstract Recursos extraer();

    
    public void ponerEnJuego() {
        if (this.sePuedeCrear()) {
        	propietario.gastaRecursos(coste);
            propietario.newFicha2(this);
            
            fuenteDeRecursos =
                    (FuenteDeRecurso) tablero.getFichaTerrestre(coordenada2);
            tablero.eliminarFicha(coordenada2);
            
            tablero.insertar(coordenada2, this);
            
        }
    }
    
    public boolean sePuedeCrear() throws NoSePuedeCrearFicha {
        try {
            super.sePuedeCrear();
            throw new EdificioDeRecursosNecesitaFichaRecursoException();
        	
        } catch (FichaSobreOtraFichaException e) {
            if (!(tablero.getFicha(coordenada2).tipoDeFuentaDeRecursos() == tipoDeFuenteDeRecursosQueNecesito)) {
                throw new EdificioDeRecursosNecesitaFichaRecursoException();
            }
            return true;
        }
    }
    
    public void  muerete() {
    	super.muerete();
    	fuenteDeRecursos.ponerEnJuego();
    }
}

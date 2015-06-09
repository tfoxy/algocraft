package estrategia.ficha.moduloDeEstrategias;

import error.NoSePuedeCrearFicha;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;
import ficha.UnidadAerea;
import ficha.UnidadTerrestre;

public class ModuloConstruccionOP {

    public void PonerEnJuego(Ficha nueva) {
        // para el polimorfismo.
    }
//No importan. Los recursos ni la Tecnologia.. es un Digamos Word Editor

    
    // Fichaterrestre.
    
    public void PonerEnJuego(FichaTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
        }
    }

    public boolean sePuedeCrear(FichaTerrestre nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacioTerreste(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        return true;
    }
    // Fichaterrestre.

    // UnidadTerrestre.
    
    public void PonerEnJuego(UnidadTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
        	nueva.propietario().recursos().poblacion().aumentarActualForzadamente(nueva.coste().poblacion());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
        }
    }

    public boolean sePuedeCrear(UnidadTerrestre nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacioTerreste(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        return true;
    }
    // UnidadTerrestre.
    
    // UnidadArrea.
    
    public void PonerEnJuego(UnidadAerea nueva) {
        if (this.sePuedeCrear(nueva)) {
        	nueva.propietario().recursos().poblacion().aumentarActualForzadamente(nueva.coste().poblacion());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
        }
    }

    public boolean sePuedeCrear(UnidadAerea nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacioArreo(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        return true;
    }
    // UnidadArrea.
    
    //recursoTerrestre
    public void PonerEnJuego(FuenteDeRecurso nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
        }
    }
    
    public boolean sePuedeCrear(FuenteDeRecurso nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacioTerreste(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        return true;
    }

    //recursoTerrestre
    
    //ExtrearRecursos
    public boolean sePuedeCrear(EdifcioDeRecusosTerrestre nueva) throws NoSePuedeCrearFicha {
        Ficha recurso = nueva.tablero().getCasilla(nueva.coordenada()).getFichaTerrestre();

        if (!(recurso.tipoDeFuentaDeRecursos() == nueva.tipoDeFuentaDeRecursos())) {
            throw new NoSePuedeCrearFicha("No Es el Racurso Correcto");
        }
        return true;
    }

    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) { //aun asi Se tiene que Crear la Fuente de Recursos
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            
            FuenteDeRecurso fuenteDeRecursos = (FuenteDeRecurso) nueva.tablero().getCasilla(nueva.coordenada()).getFichaTerrestre(); //si no puedo quitar el casteo
            nueva.tablero().getCasilla(nueva.coordenada()).eliminarFichaTerrestre();
            
            nueva.fuenteDeRecursos(fuenteDeRecursos);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
            
        }
    }
    //ExtrearRecursos
}

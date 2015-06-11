package estrategia.ficha.moduloDeEstrategias;

import error.NoSePuedeCrearFicha;
import ficha.*;

public class ModuloConstruccionOP {

//No importan. Los recursos ni la Tecnologia.. es un Digamos Word Editor


    public boolean sePuedeCrear(Ficha nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacio(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        return true;
    }

    // Fichaterrestre.

    public void PonerEnJuego(Ficha nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }
    // Fichaterrestre.

    // UnidadTerrestre.

    public void PonerEnJuego(UnidadTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().recursos().poblacion().aumentarActualForzadamente(nueva.coste().poblacion());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }

    // UnidadArrea.

    public void PonerEnJuego(UnidadAerea nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().recursos().poblacion().aumentarActualForzadamente(nueva.coste().poblacion());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }
    // UnidadArrea.

    //recursoTerrestre
    public void PonerEnJuego(FuenteDeRecurso nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }
    //recursoTerrestre

    //ExtrearRecursos
    public boolean sePuedeCrear(EdifcioDeRecusosTerrestre nueva) throws NoSePuedeCrearFicha {
        Ficha recurso = nueva.tablero().getFicha(nueva.coordenada());

        if (!(recurso.tipoDeFuentaDeRecursos() == nueva.tipoDeFuentaDeRecursos())) {
            throw new NoSePuedeCrearFicha("No Es el Racurso Correcto");
        }
        return true;
    }

    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        //aun asi Se tiene que Crear la Fuente de Recursos
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);

            //si no puedo quitar el casteo
            FuenteDeRecurso fuenteDeRecursos = (FuenteDeRecurso) nueva.tablero().getFicha(nueva.coordenada());
            nueva.tablero().eliminarFicha(nueva.coordenada());

            nueva.fuenteDeRecursos(fuenteDeRecursos);
            nueva.tablero().insertar(nueva.coordenada(), nueva);

        }
    }
    //ExtrearRecursos
}

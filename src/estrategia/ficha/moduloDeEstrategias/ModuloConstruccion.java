package estrategia.ficha.moduloDeEstrategias;


import error.FichaSobreOtraFichaException;
import error.NoSePuedeCrearFicha;
import error.RecursosInsuficientesException;
import error.TecnologiasInsuficientesException;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;


public class ModuloConstruccion {


    public void PonerEnJuego(Ficha nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }


    public boolean sePuedeCrear(Ficha nueva) throws NoSePuedeCrearFicha {
        if (!(nueva.propietario().tengoSuficientesRecursos(nueva.coste()))) { //!
            throw new RecursosInsuficientesException();
        }
        if (!nueva.tablero().hayEspacio(nueva.coordenada())) {
            throw new FichaSobreOtraFichaException();
        }
        if (!(nueva.propietario().tienesLasTecnologias(nueva.tecnologiasNecesarias()))) {
            throw new TecnologiasInsuficientesException();
        }
        return true;
    }
    // Fichaterrestre.

    //recursoTerrestre
    public void PonerEnJuego(FuenteDeRecurso nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }
    
    public boolean sePuedeCrear(FuenteDeRecurso nueva) throws NoSePuedeCrearFicha {
        if (!nueva.tablero().hayEspacioTerreste(nueva.coordenada())) {
            throw new FichaSobreOtraFichaException();
        }
        return true;
    }

    //recursoTerrestre
    
    //ExtrearRecursos
    public boolean sePuedeCrear(EdifcioDeRecusosTerrestre nueva) throws NoSePuedeCrearFicha {
        if (!(nueva.propietario().tengoSuficientesRecursos(nueva.coste()))) {
            throw new RecursosInsuficientesException();
        }
        Ficha recurso = nueva.tablero().getFichaTerrestre(nueva.coordenada());

        if (!(recurso.tipoDeFuentaDeRecursos() == nueva.tipoDeFuentaDeRecursos())) {
            throw new NoSePuedeCrearFicha("No Es el Recurso Correcto");
        }
        if (!(nueva.propietario().tienesLasTecnologias(nueva.tecnologiasNecesarias()))) {
            throw new TecnologiasInsuficientesException();
        }
        return true;
    }

    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha2(nueva);
            
            FuenteDeRecurso fuenteDeRecursos = (FuenteDeRecurso) nueva.tablero().getFichaTerrestre(nueva.coordenada()); //si no puedo quitar el casteo
            nueva.tablero().eliminarFicha(nueva.coordenada());
            
            nueva.fuenteDeRecursos(fuenteDeRecursos);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
            
        }
    }
    //ExtrearRecursos


    //la idea es que se cambia entre dos estrategias cuando se crea.
    //Dos extrategias con distintos modulos.
    public void pasarTurno(Ficha nueva) {
        nueva.turnosParaCrear(nueva.turnosParaCrear() - 1);
    }

    //se supone que la estrategia de construccion llama esto todo los turnos
    public boolean estaCreada(Ficha nueva) {
        return (nueva.turnosParaCrear() <= 0);
    }

}

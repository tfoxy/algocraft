package estrategia.ficha.moduloDeEstrategias;


import juego.Jugador;
import tablero.Coordenada;
import tablero.Tablero;
import error.NoSePuedeCrearFicha;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;


public class ModuloConstruccion {



    public void PonerEnJuego(Ficha nueva) {
        // para el polimorfismo.
    }


    // Fichaterrestre.
    
    public void PonerEnJuego(FichaTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha2(nueva);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
        }
    }

    public boolean sePuedeCrear(FichaTerrestre nueva) throws NoSePuedeCrearFicha {
        if (!(nueva.propietario().tengoSuficientesRecursos(nueva.coste()))) { //!
            throw new NoSePuedeCrearFicha("Faltan Recursos");
        }
        if (!nueva.tablero().hayEspacioTerreste(nueva.coordenada())) {
            throw new NoSePuedeCrearFicha("Espacio Ocupado");
        }
        if (!(nueva.propietario().tienesLasTecnologias(nueva.tecnologiasNecesarias()))) {
            throw new NoSePuedeCrearFicha("No Tienes las tecnologias");
        }
        return true;
    }
    // Fichaterrestre.

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
        if (!(nueva.propietario().tengoSuficientesRecursos(nueva.coste()))) {
            throw new NoSePuedeCrearFicha("Faltan Recursos");
        }
        Ficha recurso = nueva.tablero().getCasilla(nueva.coordenada()).getFichaTerrestre();

        if (!(recurso.tipoDeFuentaDeRecursos() == nueva.tipoDeFuentaDeRecursos())) {
            throw new NoSePuedeCrearFicha("No Es el Racurso Correcto");
        }
        if (!(nueva.propietario().tienesLasTecnologias(nueva.tecnologiasNecesarias()))) {
            throw new NoSePuedeCrearFicha("No Tienes las tecnologias");
        }
        return true;
    }

    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha2(nueva);
            
            FuenteDeRecurso fuenteDeRecursos = (FuenteDeRecurso) nueva.tablero().getCasilla(nueva.coordenada()).getFichaTerrestre(); //si no puedo quitar el casteo
            nueva.tablero().getCasilla(nueva.coordenada()).eliminarFichaTerrestre();
            
            nueva.fuenteDeRecursos(fuenteDeRecursos);
            nueva.tablero().insertar2(nueva.coordenada(), nueva);
            
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

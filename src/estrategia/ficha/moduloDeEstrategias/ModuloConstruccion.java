package estrategia.ficha.moduloDeEstrategias;


import error.NoSePuedeCrearFicha;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;


public class ModuloConstruccion {

// por ahora va solo el bascio de tierra.


    public void PonerEnJuego(Ficha nueva) {
        // para el polimorfismo.
    }


    // Fichaterrestre.
    public void PonerEnJuego(FichaTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);
        }
    }

    public boolean sePuedeCrear(FichaTerrestre nueva) throws NoSePuedeCrearFicha {
        if (!(nueva.propietario().tengoSuficientesRecursos(nueva.coste()))) {
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


    //ExtrearRecursos
    public boolean mePuedeCrear(EdifcioDeRecusosTerrestre nueva) throws NoSePuedeCrearFicha {
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

    public void creame(EdifcioDeRecusosTerrestre nueva) {
        if (this.sePuedeCrear(nueva)) {
            nueva.propietario().gastaRecursos(nueva.coste());
            nueva.propietario().newFicha(nueva);
            nueva.tablero().insertar(nueva.coordenada(), nueva);

            Ficha recurso = nueva.tablero().getCasilla(nueva.coordenada()).getFichaTerrestre();
            // TODO evitar casteo de recurso de Ficha a ModuloEfectosDeTurno
            nueva.fuenteDeRecursos((FuenteDeRecurso) recurso);

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
        return (nueva.turnosParaCrear() == 0);
    }

}

package ficha;

import error.EdificioDeRecursosNecesitaFichaRecursoException;
import error.FichaSobreOtraFichaException;
import juego.Recursos;
import juego.RecursosDeJugador;

public abstract class EdifcioDeRecursosTerrestre extends EdificioTerrestre {

    public EdifcioDeRecursosTerrestre() {
        this.estrategia = new EdifcioDeRecursosStrategy();
    }

    protected class EdifcioDeRecursosStrategy extends FichaStrategy {
        private RecursosDeJugador recursosVirgenes() {
            return fuenteDeRecursos.recursosVirgenes();
        }

        private boolean sePuedeEstrear() {
            return recursosVirgenes().haySuficienteRecursos(recursosExtraidosPorTurno);
        }

        @Override
        public void validarCreacion() {
            try {
                super.validarCreacion();
            } catch (FichaSobreOtraFichaException e) {
                throw new EdificioDeRecursosNecesitaFichaRecursoException();
            }
        }

        @Override
        public void pasarTurno() {
            super.pasarTurno();

            final Recursos cantidadAExtraer;

            if (this.sePuedeEstrear()) {
                cantidadAExtraer = recursosExtraidosPorTurno;
            } else {
                cantidadAExtraer = recursosVirgenes().dameRecursosLineales();
            }

            propietario.agregarRecursos(cantidadAExtraer);
            recursosVirgenes().gastar(cantidadAExtraer);
        }
    }


    @Override
    public void ponerEnJuego() {
        Ficha fichaAux = tablero.getFichaTerrestre(coordenada);
        super.ponerEnJuego();
        fuenteDeRecursos = (FuenteDeRecurso) fichaAux;
    }

    @Override
    public void muerete() {
        super.muerete();
        fuenteDeRecursos.ponerEnJuego();
    }
}

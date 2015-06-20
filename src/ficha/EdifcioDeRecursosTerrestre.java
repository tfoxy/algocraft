package ficha;

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
        public void pasarTurno() {
            super.pasarTurno();

            if (this.sePuedeEstrear()) {
                propietario.agregarRecursos(recursosExtraidosPorTurno);
                recursosVirgenes().gastar(recursosExtraidosPorTurno);
            } else {
                propietario.agregarRecursos(recursosVirgenes().dameRecursosLineales());
            }
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

package ficha;

public abstract class EdifcioDeRecursosTerrestre extends EdificioTerrestre {

    public EdifcioDeRecursosTerrestre() {
        this.estrategia = new EdifcioDeRecursosStrategy();
    }

    protected class EdifcioDeRecursosStrategy extends FichaStrategy {
        private boolean sePuedeEstrear() {
            return fuenteDeRecursos.recursosVirgenes().haySuficienteRecursos(recursosExtraidosPorTurno);
        }

        @Override
        public void pasarTurno() {
            super.pasarTurno();

            if (this.sePuedeEstrear()) {
                propietario.agregarRecursos(recursosExtraidosPorTurno);
                fuenteDeRecursos.recursosVirgenes().gastar(recursosExtraidosPorTurno);
            } else {
                propietario.agregarRecursos(fuenteDeRecursos.recursosVirgenes().dameRecursosLineales());
            }
        }
    }


    @Override
    public void ponerEnJuego() {
        Ficha ficha = tablero.getFichaTerrestre(coordenada);
        super.ponerEnJuego();
        fuenteDeRecursos = (FuenteDeRecurso) ficha;
    }

    @Override
    public void muerete() {
        super.muerete();
        fuenteDeRecursos.ponerEnJuego();
    }
}

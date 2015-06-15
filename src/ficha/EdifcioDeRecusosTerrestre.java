package ficha;

import error.EdificioDeRecursosNecesitaFichaRecursoException;
import error.FichaSobreOtraFichaException;
import error.NoSePuedeCrearFicha;
import juego.Recursos;

public abstract class EdifcioDeRecusosTerrestre extends EdificioTerrestre {

    public abstract Recursos extraer();


    @Override
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

    @Override
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

    @Override
    public void muerete() {
        super.muerete();
        fuenteDeRecursos.ponerEnJuego();
    }

    @Override
    public void pasarTurno() {
        super.pasarTurno();

        if (estoyConstruido) {
            if (this.sePuedeEstrear()) {
                propietario.agregarRecursos2(recursosExtraidosPorTurno);
                fuenteDeRecursos.recursosVirgenes().gastar(recursosExtraidosPorTurno);
            } else {
                propietario.agregarRecursos2(fuenteDeRecursos.recursosVirgenes().DameRecursosLineales());
            }
        }
    }

    private boolean sePuedeEstrear() {
        return fuenteDeRecursos.recursosVirgenes().haySuficienteRecursos(recursosExtraidosPorTurno);
    }
}

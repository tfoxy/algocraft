package estrategia.ficha.moduloDeEstrategias;

import error.FueraDeRangoException;
import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import stats.Ataque;
import tablero.Coordenada;


public class ModuloAtacarYSerAtacado {

    public void serAtacado(int danio, Ficha defensor) {
        defensor.barras().sufrirDanio(danio, defensor);
    }


    public void realizarAtaque(Ficha agresor, Ficha defensor) {
        final Ataque ataque = defensor.tipoDeAtaqueRecibido(agresor);

        if (!this.puedoAtacar(agresor, defensor, ataque.rango())) {
            throw new FueraDeRangoException();
        }

        this.serAtacado(ataque.danio(), defensor);
    }


    public boolean atacar(Ficha agresor, Ficha defensor) {
        try {
            this.realizarAtaque(agresor, defensor);
            return true;
        } catch(FueraDeRangoException e) {
            return false;
        }
    }


    private boolean puedoAtacar(Ficha agresor, Ficha defensor, int rango) {
        Coordenada posicionAgresor = agresor.coordenada();
        Coordenada posicionDefensor = defensor.coordenada();

        return rango >= posicionAgresor.distanciaAObjetivo(posicionDefensor);
    }
}


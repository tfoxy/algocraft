package estrategia.ficha.moduloDeEstrategias;

import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import stats.Ataque;
import tablero.Coordenada;


public class ModuloAtacarYSerAtacado {

    public void serAtacado(int danio, Ficha defensor) {
        defensor.barras().sufrirDanio(danio, defensor);
    }


    public boolean atacar(Ficha agresor, Ficha defensor) {
        final Ataque ataque = defensor.tipoDeAtaqueRecibido(agresor);

        if (this.puedoAtacar(agresor, defensor, ataque.rango())) {
            this.serAtacado(ataque.danio(), defensor);
            return true;
        }
        return false;
    }


    private boolean puedoAtacar(Ficha agresor, Ficha defensor, int rango) {
        Coordenada posicionAgresor = agresor.coordenada();
        Coordenada posicionDefensor = defensor.coordenada();

        return rango >= posicionAgresor.distanciaAObjetivo(posicionDefensor);
    }
}


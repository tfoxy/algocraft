package estrategia.ficha.moduloDeEstrategias;

import ficha.Ficha;
import ficha.FichaAerea;
import ficha.FichaTerrestre;
import tablero.Coordenada;


public class ModuloAtacarYSerAtacado {

    private int alcanse;

    public void serAtacado(int danio, Ficha defensor) {
        defensor.barras().sufrirDanio(danio, defensor);
    }

    public boolean atacar(Ficha agresor, Ficha defensor) {
        return false;
        //para el polimorfismo
    }


    public boolean atacar(Ficha agresor, FichaTerrestre defensor) {

        alcanse = agresor.rangoDeAtaqueTierra();
        if (this.puedoAtacar(agresor, defensor)) {
            this.serAtacado(agresor.ataqueTierra(), defensor);
            return true;
        }
        return false;
    }

    public boolean atacar(Ficha agresor, FichaAerea defensor) {

        alcanse = agresor.rangoDeAtaqueAire();
        if (this.puedoAtacar(agresor, defensor)) {
            this.serAtacado(agresor.ataqueAire(), defensor);
            return true;
        }
        return false;
    }
    //se puede ahoraar unas 3 lineas mas de codigo... pero ya qudaria confuso.


    private boolean puedoAtacar(Ficha agresor, Ficha defensor) {

        Coordenada posicionAgresor = agresor.coordenada();
        Coordenada posicionDefensor = defensor.coordenada();

        if (alcanse >= posicionAgresor.distanciaAObjetivo(posicionDefensor)) {
            return true;
        }
        return false;
    }
}


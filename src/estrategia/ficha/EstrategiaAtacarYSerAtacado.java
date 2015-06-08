package estrategia.ficha;

import tablero.Coordenada;
import ficha.FichaAerea;
import ficha.FichaTerrestre;

public class EstrategiaAtacarYSerAtacado {

    int alcanse;

    public void serAtacado(int danio, FichaDeJugador defensor) {
        defensor.getbarras().sufrirDanio(danio, defensor);
    }

    public boolean atacado(FichaDeJugador agresor, FichaTerrestre defensor) {

        alcanse = agresor.getRangoDeAtaqueTierra();
        if (this.puedoAtacar(agresor, defensor)) {
            this.serAtacado(defensor.getAtaqueTierra(), defensor);
            return true;
        }
        return false;
    }

    public boolean atacado(FichaDeJugador agresor, FichaAerea defensor) {

        alcanse = agresor.getRangoDeAtaqueAire();
        if (this.puedoAtacar(agresor, defensor)) {
            this.serAtacado(defensor.getAtaqueAire(), defensor);
            return true;
        }
        return false;
    }
    //se puede ahoraar unas 3 lineas mas de codigo... pero ya qudaria confuso.


    private boolean puedoAtacar(Ficha agresor, Ficha defensor) {

        Coordenada posicionAgresor = agresor.getCoordenada();
        Coordenada posicionDefensor = defensor.getCoordenada();

        if (alcanse >= posicionAgresor.distanciaAObjetivo(posicionDefensor)) {
            return true;
        }
        return false;
    }
}

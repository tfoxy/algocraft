package ficha.natural.terreno;

import ficha.FichaTerrestre;
import ficha.TipoDeFicha;

public class TerrenoEspacial extends FichaTerrestre {

    public TerrenoEspacial() {
        this.tipoDeFicha.add(TipoDeFicha.TERRENO);
        this.tipoDeFicha.add(TipoDeFicha.NATURAL);
    }
}

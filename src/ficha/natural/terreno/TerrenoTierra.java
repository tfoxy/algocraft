package ficha.natural.terreno;

import ficha.FichaTerrestre;
import ficha.TipoDeFicha;

public class TerrenoTierra extends FichaTerrestre {

    public TerrenoTierra() {
        this.tipoDeFicha.add(TipoDeFicha.TERRENO);
        this.tipoDeFicha.add(TipoDeFicha.VACIA);
        this.tipoDeFicha.add(TipoDeFicha.NATURAL);
    }
}

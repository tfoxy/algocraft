package ficha.natural.terreno;

import ficha.FichaAerea;
import ficha.TipoDeFicha;

public class TerrenoAire extends FichaAerea {

    public TerrenoAire() {
        this.tipoDeFicha.add(TipoDeFicha.TERRENO);
        this.tipoDeFicha.add(TipoDeFicha.VACIA);
        this.tipoDeFicha.add(TipoDeFicha.NATURAL);
    }
}

package ficha.natural.terreno;

import ficha.FichaCelestial;
import ficha.TipoDeFicha;

public class TerrenoCielo extends FichaCelestial {

    public TerrenoCielo() {
        this.tipoDeFicha.add(TipoDeFicha.TERRENO);
        this.tipoDeFicha.add(TipoDeFicha.VACIA);
        this.tipoDeFicha.add(TipoDeFicha.CELESTIAL);
    }
}

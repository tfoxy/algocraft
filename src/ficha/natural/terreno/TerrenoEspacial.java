package ficha.natural.terreno;

import ficha.FichaTerrestre;
import ficha.TipoDeFicha;

import java.awt.Color;

public class TerrenoEspacial extends FichaTerrestre {

    public TerrenoEspacial() {
        this.tipoDeFicha.add(TipoDeFicha.TERRENO);
        this.tipoDeFicha.add(TipoDeFicha.NATURAL);
    }

    @Override
    public Color miColor() {
        return Color.GRAY;
    }
}

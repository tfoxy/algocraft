package magia;

/**
 * Tira un misil que al impactar en un radio, causa que las ​unidades enemigas
 * pierdan su energía (para las unidades mágicas)
 * o su escudo en el caso de los protoss.
 * Cuesta 100 energía.
 * El EMP NO afecta a los edificios.
 */
public class EmpMagia extends Magia {

    public EmpMagia() {
        super(100, 6);
    }

}

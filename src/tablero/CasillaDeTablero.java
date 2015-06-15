package tablero;

import error.FichaSobreOtraFichaException;
import ficha.FichaTerrestre;
import ficha.FichaAerea;

import java.util.EnumMap;

public class CasillaDeTablero implements Casilla {

    private FichaTerrestre fichaTerreste;
    private FichaAerea fichaAerea;

    private EnumMap<Direccion, Casilla> casillasAdyacentes =
            new EnumMap<>(Direccion.class);

    public CasillaDeTablero() {
        fichaTerreste = new FichaTerrestre();
        fichaAerea = new FichaAerea();

        for (Direccion direccion : Direccion.values()) {
            insertarCasillaAdyacente(direccion, new CasillaInexistente());
        }
    }

    void insertarCasillaAdyacente(Direccion direccion, Casilla casilla) {
        casillasAdyacentes.put(direccion, casilla);
    }

    public boolean estaVacia() {
        return (fichaTerreste.estoyVacio() && fichaAerea.estoyVacio());
    }

    @Override
    public boolean hayEspacioTerreste() {
        return fichaTerreste.estoyVacio();
    }

    @Override
    public boolean hayEspacioArreo() { //new 6
        return fichaAerea.estoyVacio();
    }

    @Override
    public FichaTerrestre getFichaTerrestre() {
        return fichaTerreste;
    }

    @Override
    public FichaAerea getFichaAerea() {
        return fichaAerea;
    }

    @Override
    public void insertar(FichaTerrestre ficha) {
        if (!fichaTerreste.estoyVacio()) {
            throw new FichaSobreOtraFichaException();
        } else {
            fichaTerreste = ficha;
        }
    }

    @Override
    public void insertar(FichaAerea ficha) {
        if (!fichaAerea.estoyVacio()) {
            throw new FichaSobreOtraFichaException();
        } else {
            fichaAerea = ficha;
        }
    }

    @Override
    public void eliminarFichaTerrestre() {
        fichaTerreste = new FichaTerrestre();
    }

    @Override
    public void eliminarFichaAerea() {
        fichaAerea = new FichaAerea();
    }

    @Override
    public Casilla get(Direccion direccion) {
        return casillasAdyacentes.get(direccion);
    }


}

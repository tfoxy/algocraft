package magia;

import error.UnicamenteObjetivoAliadoException;
import ficha.Ficha;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Tablero;

/**
 * Es capaz de crear 2 copias de una propia unidad que son
 * simples “copias falsas” Son unidades que simulan ser unidades propias
 * pero al atacar no causan daño. Se crean para que reciban daño.
 * Los enemigos no pueden distinguir una unidad alucinada de una real.
 * Cuesta 100 de energía.
 * Las unidades alucinadas ​NO​ tienen vida, solo escudo.
 */
public class AlucinacionMagia extends Magia {

    private Ficha fichacopiar = null;

    public AlucinacionMagia() {
        super(100, 4);
    }

    @Override
    protected void verificarObjetivo(Ficha ficha, Coordenada3d objetivo) {
        Tablero mapa = ficha.tablero();
        Ficha fichaObjetivo = mapa.getFicha(objetivo);

        if (!fichaObjetivo.propietario().equals(ficha.propietario())) {
            // TODO test para probar que solamente se puede aplicar a ficha del mismo jugador
            throw new UnicamenteObjetivoAliadoException();
        }
    }

    //hay que pensar como seria esto en la ineterfas.. Pero deveria ser 2 funciones.

    public void selccionarEspectro(Ficha objetivo) {
        fichacopiar = objetivo;
    }

    public void insertarEspectro(Ficha objetivo, Coordenada lugar) {
        Ficha alucinacion = fichacopiar.espectro();
        alucinacion.setBasico(alucinacion.propietario(), alucinacion.tablero(), lugar);

        //tengo que pensar algo mas proligo despues. Aunque este es un exepcion a la regla.
        alucinacion.propietario().newFicha(alucinacion);
        alucinacion.tablero().insertar(alucinacion);
    }
    
    /*pero falta ver si el usario elige el lugar o sale alado de la ficha.
     *  Voy a usar area de cordenada
     *  y resolverlo como un repetir para toda la lista y un if.
     */

}

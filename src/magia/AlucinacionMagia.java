package magia;

import java.util.HashSet;
import java.util.Set;

import error.UnicamenteObjetivoPropioException;
import ficha.Ficha;
import tablero.Coordenada;
import tablero.Coordenada3d;
import tablero.Direccion;
import tablero.ITablero;

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
        ITablero mapa = ficha.tablero();
        Ficha fichaObjetivo = mapa.getFicha(objetivo);
        
        
        
        if (!fichaObjetivo.propietario().equals(ficha.propietario())) {
            // TODO test para probar que solamente se puede aplicar a ficha del mismo jugador
            throw new UnicamenteObjetivoPropioException();
        }
    }

    //hay que pensar como seria esto en la ineterfas.. Pero deveria ser 2 funciones.
   
    @Override
    public void realizar(Ficha ficha, Coordenada3d objetivo) { //lansa throw new UnicamenteObjetivoPropioException();
        super.realizar(ficha, objetivo);
        verificarObjetivo(ficha, objetivo);
        selccionarEspectro(ficha);
        
        int i = 2;
        Set<Coordenada> casillasVisibles = coordenadasPosibles(objetivo.proyeccion());
        for (Coordenada coordenada: casillasVisibles) {
            if (i < 0){
                if (ficha.tablero().hayEspacio(new Coordenada3d( coordenada, objetivo.getZ()))){
                	this.insertarEspectro(coordenada);
                	i = i - 1;
                }
            	
            }
            
        }
    
    	if (i!=0){ //si quieres tirer error por no tirar sufcientes ilus. Irea aca.
    	}
    }
    
    public Set<Coordenada> coordenadasPosibles(Coordenada objetivo) {
        Set<Coordenada> casillasVisibles = new HashSet<>();
        casillasVisibles.add(objetivo.dameCordenadaHacia(Direccion.ARRIBA));
        casillasVisibles.add(objetivo.dameCordenadaHacia(Direccion.ABAJO));
        casillasVisibles.add(objetivo.dameCordenadaHacia(Direccion.DERECHA));
        casillasVisibles.add(objetivo.dameCordenadaHacia(Direccion.IZQUIERDA));
        return casillasVisibles;
    } 

    
    
    public void selccionarEspectro(Ficha objetivo) {
        fichacopiar = objetivo;
    }

    public void insertarEspectro(Coordenada lugar) {
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

package estrategia.ficha;

import estrategia.ficha.moduloDeEstrategias.ModuloConstruccion;
import ficha.CasaTerrestre;
import ficha.EdifcioDeRecusosTerrestre;
import ficha.EdificioTerrestre;
import ficha.Ficha;
import ficha.FichaTerrestre;
import ficha.FuenteDeRecurso;

public class EstrategiaConsturccion extends EstrategiaFicha {

    private ModuloConstruccion moduloConstruccion = new ModuloConstruccion();


    public void PonerEnJuego(Ficha nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
    }
    public void PonerEnJuego(FuenteDeRecurso nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
    }
    public void PonerEnJuego(EdifcioDeRecusosTerrestre nueva) {
        moduloConstruccion.PonerEnJuego(nueva);
    }
    
    public void matar(CasaTerrestre ficha) {
        moduloMorir.morir((EdificioTerrestre)ficha);
        /*se que el casteo esta mal. pero en este caso nos ahorra unas 5 lineas de codigo repetido. 
         * Ya que cuando la casa muere antes de ser construida deve morir como un edificio. 
         * La otra opcion seria agregar la poblcaion que se pierde o poner una opcion de morir mas (ambas implica duplicar codigo). 
         * Aparte en este caso estoy Casterando de verdad. Ya que bucso que la Casa no se la trate como Casa.
         */
    }
    
    public EstrategiaFicha pasarTurno(Ficha ficha) {
        // TODO llenar vida de a poco
        moduloConstruccion.pasarTurno(ficha);
        if (moduloConstruccion.estaCreada(ficha)) {
            ficha.propietario().agregarPoblacionTotal(ficha.poblacionQueDa()); //esto solo hace algo en las casas.
            return new EstrategiaFichaViva();
        }
        return this;
    }
}

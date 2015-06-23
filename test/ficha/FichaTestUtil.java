package ficha;

public final class FichaTestUtil {

    private FichaTestUtil() {
        // Utility class
    }

    public static void cumplirCondicionesDelJugadorParaConstruccion(Ficha ficha) {
        ficha.propietario().agregarRecursos(ficha.coste());
        ficha.propietario().agregarPoblacionTotal(ficha.coste().poblacion());
        ficha.propietario().agregarTecnologias(ficha.tecnologiasNecesarias());
        ficha.propietario().visibilidad().verDesde(ficha.coordenada(), 0);
    }

}

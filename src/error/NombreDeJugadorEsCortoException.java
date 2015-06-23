package error;

import juego.Jugador;

public class NombreDeJugadorEsCortoException extends JuegoException {
    public NombreDeJugadorEsCortoException() {
        super(defaultMessage());
    }

    public NombreDeJugadorEsCortoException(String msg) {
        super(msg);
    }

    private static String defaultMessage() {
        String msgFormat = "El nombre de jugador debe contener al menos %s caracteres";
        return String.format(msgFormat, Jugador.LONGITUD_MINIMA_DE_NOMBRE);
    }
}
